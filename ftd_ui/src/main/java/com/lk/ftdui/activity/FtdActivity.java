package com.lk.ftdui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.lk.ftd_base.CameraFragment;
import com.lk.ftd_base.constant.Constant;
import com.lk.ftd_base.constant.Step;
import com.lk.ftdui.R;

import java.util.ArrayList;

public class FtdActivity extends BaseActivity implements CameraFragment.OnCaptureCompleteListener {

    @Override
    protected boolean setPBDefault() {
        return false;
    }

    public static void getPicFromCamera(Context context, String[] stepIDs) {
        Intent intent = new Intent(context, FtdActivity.class);
        intent.putExtra("stepIDs", stepIDs);
        context.startActivity(intent);
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        Intent intent = getIntent();
        String[] stepIDs = intent.getStringArrayExtra("stepIDs");
        CameraFragment cf = CameraFragment.newInstance(stepIDs);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, cf);
        ft.commit();
    }

    @Override
    protected String setTitle() {
        return "面诊";
    }

    @Override
    public int setContentViewResId() {
        return R.layout.activity_ftd;
    }

//    @Override
//    public void onCaptureComplete(FtdResult[] results) {
//        Intent intent = new Intent(this, FileUploadActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public void onStepComplete(Step step) {
        String title = step.getTitle();
        tbTvTitle.setText(title);
    }

    @Override
    public void onCaptureComplete(ArrayList<Step> stepList) {
        Intent intent = new Intent(this, FileUploadActivity.class);
        intent.putExtra("stepResult",stepList);
        startActivity(intent);
        finish();
    }
}
