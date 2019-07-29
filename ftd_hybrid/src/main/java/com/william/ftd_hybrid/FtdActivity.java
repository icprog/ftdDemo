package com.william.ftd_hybrid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class FtdActivity extends AppCompatActivity implements CameraFragment.OnCaptureCompleteListener {


    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;
    public static final int ASK = 3;


    @IntDef(value = {FACE, TONGUE_TOP, TONGUE_BOTTOM, ASK})
    @interface Step {
    }

    public static void getPicFromCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, FtdActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void getPicFromCamera(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), FtdActivity.class);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ftd_h);


    }

    @Override
    public void onCaptureComplete(String[] paths) {
        Intent intent = new Intent();
        intent.putExtra("paths", paths);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
