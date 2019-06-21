package com.william.ftdui;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.laikang.jtcameraview.CameraStateListener;
import com.laikang.jtcameraview.JTCameraView;

public class CameraFragment extends Fragment implements CameraStateListener, View.OnClickListener {

    private static final String KEY_DRAWABLE_ID = "drawableId";
    private static final String REQUEST_ID = "requestId";

    private int drawableId;
    private int requestId;

    private OnFragmentInteractionListener mListener;

    private JTCameraView mJTCameraView;
    private ImageButton btnCapture;
    private ImageView iv;

    private boolean canPreview;

    private static final int REQUEST_CAMERA_PERMISSION = 100;


    public CameraFragment() {
        // Required empty public constructor
    }

    public static CameraFragment newInstance(@DrawableRes int drawableId, int requestId) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_DRAWABLE_ID, drawableId);
        args.putInt(REQUEST_ID, requestId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        canPreview = isVisibleToUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drawableId = getArguments().getInt(KEY_DRAWABLE_ID);
            requestId = getArguments().getInt(REQUEST_ID);
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
        this.mJTCameraView = rootView.findViewById(R.id.jtcv);
        this.mJTCameraView.setListener(this);
        this.btnCapture = rootView.findViewById(R.id.btn_capture);
        this.btnCapture.setOnClickListener(this);
        this.iv = rootView.findViewById(R.id.iv_dashed);
        this.iv.setImageResource(this.drawableId);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            startPreview();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            startPreview();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    @Override
    public void onCupture(Bitmap bitmap) {
        mListener.onCaptrueComplete(this.requestId);
    }

    @Override
    public void onCameraClosed() {

    }

    private void startPreview() {
        if (canPreview) {
            mJTCameraView.startPreview();
        } else {
            stopPreview();
        }
    }

    private void stopPreview() {
        mJTCameraView.stopPreview();
    }

    @Override
    public void onClick(View v) {
        mJTCameraView.takePicture();
    }

    public interface OnFragmentInteractionListener {
        void onCaptrueComplete(int requestId);
    }
}
