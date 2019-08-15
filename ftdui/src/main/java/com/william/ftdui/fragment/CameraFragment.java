package com.william.ftdui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.laikang.jtcameraview.CameraStateListener;
import com.laikang.jtcameraview.JTCameraView;
import com.shuhart.stepview.StepView;
import com.william.ftdui.R;
import com.william.ftdui.activity.ReportActivity;
import com.william.ftdui.constant.Constant;
import com.william.ftdui.constant.Step;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.laikang.jtcameraview.Constants.CAMERA_FACING_BACK;
import static com.laikang.jtcameraview.Constants.CAMERA_FACING_FRONT;


public class CameraFragment extends Fragment implements CameraStateListener, View.OnClickListener {

    private static final String TAG = "CameraFragment";

    private StepView mStepView;
    private JTCameraView mJTCameraView;
    private ImageView btnCapture;
    private ImageView btnChangeFacing;
    private ImageView iv;

    private boolean canPreview;

    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private Handler mMainThreadHandler = new mMainThreadHandler(this);

    private OnCaptureCompleteListener listener;

    private ArrayList<Step> stepList;
    private int currentStepIndex;

    public static CameraFragment newInstance(ArrayList<Step> stepList) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("stepList", stepList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.canPreview = isVisibleToUser;
        if (mJTCameraView == null) {
            return;
        }
        if (isVisibleToUser) {
            startPreview();
        } else {
            stopPreview();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.stepList = args.getParcelableArrayList("stepList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    private int mFacing = CAMERA_FACING_FRONT;

    public void changeFacing() {
        if (mFacing == CAMERA_FACING_FRONT) {
            mFacing = CAMERA_FACING_BACK;
        } else {
            mFacing = CAMERA_FACING_FRONT;
        }
        mJTCameraView.setCameraFacing(mFacing);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rootView = getView();
        this.mStepView = rootView.findViewById(R.id.step);
        this.mJTCameraView = rootView.findViewById(R.id.jtcv);
        this.mJTCameraView.setListener(this);
        this.mJTCameraView.autoPreview = canPreview;
        this.btnCapture = rootView.findViewById(R.id.btn_capture);
        this.btnCapture.setOnClickListener(this);
        this.iv = rootView.findViewById(R.id.iv_dashed);
        this.iv.setImageResource(this.stepList.get(currentStepIndex).getDrawableId());
        this.btnChangeFacing = rootView.findViewById(R.id.btn_change_facing);
        btnChangeFacing.setOnClickListener(this);
        rootView.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReportActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    /**
     * 切换参照图片
     */
    private void loadImage(@Constant.StepId int stepId) {
        int currentDrawable = Constant.steps.get(stepId).getDrawableId();
        this.iv.setImageResource(currentDrawable);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBackgroundHandler();
        if (!canPreview) {
            return;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!canPreview) {
            return;
        }
        stopPreview();
        if (mBackgroundThread == null) {
            return;
        }
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            Log.e(TAG, "close: 后台线程关闭失败：", e);
        }
    }

    @Override
    public void onCameraOpend() {

    }

    @Override
    public void onPreviewStart() {

    }

    @Override
    public void onPreviewStop() {

    }

    @Override
    public void onShutter() {

    }

    private void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT);
    }

    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            mBackgroundThread = new HandlerThread("background");
            mBackgroundThread.start();
            mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
        }
        return mBackgroundHandler;
    }

    @Override
    public void onCupture(final Bitmap bitmap) {
        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                String fileName = stepList.get(currentStepIndex).getFileName();
                File file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流里面
                    os.flush();
                    os.close();
                    bitmap.recycle();
                    Message msg = mBackgroundHandler.obtainMessage();
                    msg.arg1 = currentStepIndex;
                    mMainThreadHandler.sendMessage(msg);
                } catch (IOException e) {
                    Log.w(TAG, "图像文件写入失败： " + file, e);
                }
            }
        });
    }

    @Override
    public void onCameraClosed() {

    }


    private void startPreview() {
        if (mJTCameraView != null) {
            mJTCameraView.startPreview();
        }
    }

    private void stopPreview() {
        if (mJTCameraView != null) {

            mJTCameraView.stopPreview();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_capture) {
            mJTCameraView.takePicture();
        } else {
            changeFacing();
        }
    }

    private static class mMainThreadHandler extends Handler {

        private WeakReference<CameraFragment> weakFragment;

        public mMainThreadHandler(CameraFragment cameraFragment) {
            this.weakFragment = new WeakReference<>(cameraFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CameraFragment fragment = this.weakFragment.get();
            if (fragment == null || fragment.getActivity() == null) {
                return;
            }
            int currnetStepIndex = msg.arg1;
//            Constant.STEP_TONGUE_BOTTOM
            if (currnetStepIndex < fragment.stepList.size()-1) {
                fragment.currentStepIndex += 1;
                fragment.mStepView.go(fragment.currentStepIndex, true);
                fragment.loadImage(fragment.currentStepIndex);
            } else {
                if (fragment.listener != null) {
                    fragment.listener.onCaptureComplete();
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCaptureCompleteListener) {
            this.listener = (OnCaptureCompleteListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    public interface OnCaptureCompleteListener {
        void onCaptureComplete();
    }
}
