package com.william.ftd_base;

import android.app.Activity;
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
import com.william.ftd_base.constant.Constant;
import com.william.ftd_base.constant.Step;
import com.william.zhibiaoview.StepView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import static com.laikang.jtcameraview.Constants.CAMERA_FACING_BACK;
import static com.laikang.jtcameraview.Constants.CAMERA_FACING_FRONT;


public class CameraFragment extends Fragment implements CameraStateListener, View.OnClickListener {

    private static final String TAG = "CameraFragment";

    private StepView mStepView;
    private JTCameraView mJTCameraView;
    private ImageView btnCapture;
    private ImageView btnChangeFacing;
    private ImageView ivDashed;
    private ImageView ivArea;
    private ImageView ivTip;
    private ImageView btnAlum;

    private boolean canPreview;

    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private Handler mMainThreadHandler = new mMainThreadHandler(this);

    private OnCaptureCompleteListener listener;

    private String[] stepIDs;
    private ArrayList<Step> stepList = new ArrayList<>(10);
    private AtomicInteger currentStepIndex = new AtomicInteger();

    private int mFacing = CAMERA_FACING_FRONT;

    public static CameraFragment newInstance(String[] stepIDs) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putStringArray("stepIDs", stepIDs);
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
            this.stepIDs = args.getStringArray("stepIDs");
            // 空判断
            if (stepIDs != null) {
                for (String stepID : stepIDs) {
                    this.stepList.add(Constant.steps.get(stepID));
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

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
        this.ivDashed = rootView.findViewById(R.id.iv_dashed);
        this.ivDashed.setImageResource(this.stepList.get(currentStepIndex.get()).getDashedResID());
        this.ivArea = rootView.findViewById(R.id.iv_area);
        this.ivTip = rootView.findViewById(R.id.iv_tip);
        this.ivTip.setImageResource(this.stepList.get(currentStepIndex.get()).getTipResID());
        this.btnChangeFacing = rootView.findViewById(R.id.btn_change_facing);
        btnChangeFacing.setOnClickListener(this);
        this.btnAlum = rootView.findViewById(R.id.iv_album);
        this.btnAlum.setOnClickListener(this);
    }

    /**
     * 切换参照图片
     */
    private void loadImage(int stepId) {
        int dashedResID = stepList.get(stepId).getDashedResID();
        int tipResID = stepList.get(stepId).getTipResID();
        this.ivDashed.setImageResource(dashedResID);
        this.ivTip.setImageResource(tipResID);
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
        btnCapture.setEnabled(false);
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
        final Context context = getContext();

        if (context == null) {
            return;
        }

        //截取
        final Bitmap cropBitmap = cropBitmap(bitmap);
        bitmap.recycle();

        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                String fileName = stepList.get(currentStepIndex.get()).getFileName();
                File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流里面
                    cropBitmap.recycle();
                    os.flush();
                    os.close();
                    Message msg = mBackgroundHandler.obtainMessage();
                    msg.arg1 = currentStepIndex.get();
                    mMainThreadHandler.sendMessage(msg);
                } catch (IOException e) {
                    Log.w(TAG, "图像文件写入失败： " + file, e);
                }
            }
        });
    }

    /**
     * 将拍照的原始图片缩放为与JTCameraView相似尺寸，再裁切出虚线框范围内的图片
     *
     * @param bitmap
     * @return
     */
    private Bitmap cropBitmap(Bitmap bitmap) {

        double bitmapWidth = bitmap.getWidth();
        double bitmapHeight = bitmap.getHeight();
        double bitmapArea = bitmapWidth * bitmapHeight;

        double jtWidth = mJTCameraView.getWidth();
        double jtHeight = mJTCameraView.getHeight();
        double jtArea = jtWidth * jtHeight;

        double num = Math.sqrt(bitmapArea / jtArea);

        int ivWidth = (int) (ivArea.getWidth() * num);
        int ivHeight = (int) (ivArea.getHeight() * num);

        int x = (int) (bitmapWidth - ivWidth) / 2;
        int y = (int) (bitmapHeight - ivHeight) / 2;

        //截取
        final Bitmap cropBitmap = Bitmap.createBitmap(bitmap, x, y, ivWidth, ivHeight);
        bitmap.recycle();
        return cropBitmap;
    }

    @Override
    public void onCut(File file) {

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
        int id = v.getId();
        if (id == R.id.btn_capture) {
            mJTCameraView.takePicture();

        } else if (id == R.id.iv_album) {
            goPhotoAlbum();
        } else if (id == R.id.btn_change_facing) {
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
            if (fragment.currentStepIndex.getAndIncrement() < fragment.stepList.size() - 1) {
                fragment.mStepView.setSelectedIndex(fragment.currentStepIndex.get());
                fragment.loadImage(fragment.currentStepIndex.get());
            } else {
                fragment.callbackResult();
            }
            fragment.btnCapture.setEnabled(true);
        }
    }

    private void callbackResult() {
        String fileName;
        for (int i = 0; i < stepList.size(); i++) {
            fileName = stepList.get(i).getFileName();
            File file = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
            stepList.get(i).setPhotoPath(file.getPath());
        }
        if (listener != null) {
            listener.onCaptureComplete(stepList);
        }
    }

    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == 2) {
            String path = AlbumUtil.getRealPathFromUri(getContext(),data.getData());
            stepList.get(currentStepIndex.get()).setPhotoPath(path);

            if (currentStepIndex.getAndIncrement() == stepList.size() - 1 && listener != null) {
                listener.onCaptureComplete(stepList);
            } else {
                loadImage(currentStepIndex.get());
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
        void onCaptureComplete(ArrayList<Step> stepList);
    }
}