package com.william.ftdui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.DrawableRes;
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
import com.william.ftdui.activity.FileUploadActivity;
import com.william.ftdui.constant.Constant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

public class CameraFragment extends Fragment implements CameraStateListener, View.OnClickListener {
    private static final String TAG = "CameraFragment";

    private static final String AUTO_PREVIEW = "autoPreview";
    private static final String KEY_DRAWABLE_ID = "drawableId";
    private static final String REQUEST_ID = "stepId";

    private boolean autoPreview;
    private int drawableId = R.drawable.xuxian_mian;
    private int stepId = Constant.STEP_FACE;


    private StepView mStepView;
    private JTCameraView mJTCameraView;
    private ImageView btnCapture;
    private ImageView iv;

    private boolean canPreview;

    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private Handler mMainThreadHandler = new mMainThreadHandler(this);


    public static CameraFragment newInstance(boolean autoPreview, @DrawableRes int drawableId, int requestId) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putBoolean(AUTO_PREVIEW, autoPreview);
        args.putInt(KEY_DRAWABLE_ID, drawableId);
        args.putInt(REQUEST_ID, requestId);
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
            this.autoPreview = args.getBoolean(AUTO_PREVIEW);
            this.drawableId = args.getInt(KEY_DRAWABLE_ID);
            this.stepId = args.getInt(REQUEST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
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
        this.iv.setImageResource(this.drawableId);
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
        showToast("获取拍照后的图像信息，需要自己保存");
        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                String fileName = Constant.steps.get(stepId).getFileName();
                File file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName + ".jpeg");
                try {
                    OutputStream os = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流里面
                    os.flush();
                    os.close();
                    bitmap.recycle();
                    Message msg = mBackgroundHandler.obtainMessage();
                    msg.arg1 = stepId;
                    mMainThreadHandler.sendMessage(msg);
                } catch (IOException e) {
                    Log.w(TAG, "图像文件写入失败： " + file, e);
                }
            }
        });
    }

    @Override
    public void onCupture(final byte[] data) {
        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                String fileName = Constant.steps.get(stepId).getFileName();
                File file;
                OutputStream os = null;
                try {
                    file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName + ".jpeg");
                    os = new FileOutputStream(file);
                    os.write(data);
                    os.close();
                    Message msg = mBackgroundHandler.obtainMessage();
                    msg.arg1 = stepId;
                    mMainThreadHandler.sendMessage(msg);
                } catch (IOException e) {
                    Log.w(TAG, "Cannot write to " + fileName + ".jpeg", e);
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            // Ignore
                        }
                    }
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

        mJTCameraView.takePicture();
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
            int stepId = msg.arg1;
            if (stepId < Constant.STEP_TONGUE_BOTTOM) {
                fragment.stepId += 1;
                fragment.mStepView.go(fragment.stepId, true);
                fragment.loadImage(fragment.stepId);
            } else {
                Intent intent = new Intent(fragment.getContext(), FileUploadActivity.class);
                fragment.startActivity(intent);
                fragment.getActivity().finish();
            }
        }
    }
}
