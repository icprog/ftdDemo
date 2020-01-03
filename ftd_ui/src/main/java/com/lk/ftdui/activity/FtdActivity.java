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
import com.lk.ftd_base.constant.Step;
import com.lk.ftd_core.constant.Constant1;
import com.lk.ftdui.R;
import com.lk.ftdui.activity.param.DoctorInfo;

import java.util.ArrayList;

public class FtdActivity extends BaseActivity implements CameraFragment.OnCaptureCompleteListener {

    @Override
    protected boolean setPBDefault() {
        return false;
    }

    public static void start(Context context, ArrayList<Integer> diagnoseTagList, DoctorInfo... doctorInfo) {
        Intent intent = new Intent(context, FtdActivity.class);
        intent.putExtra("diagnoseTagList", diagnoseTagList);
        if (doctorInfo.length > 0) {
            intent.putExtra("doctorInfo", doctorInfo[0]);
        }
        context.startActivity(intent);
    }

    public static void startWithThreePhoto(Context context, DoctorInfo... doctorInfo) {
        ArrayList<Integer> diagnoseTagList = new ArrayList<>(3);
        diagnoseTagList.add(Constant1.FACE);
        diagnoseTagList.add(Constant1.TONGUE_TOP);
        diagnoseTagList.add(Constant1.TONGUE_BOTTOM);
        FtdActivity.start(context, diagnoseTagList, doctorInfo);
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        Intent intent = getIntent();
        ArrayList<Integer> diagnoseTagList = intent.getIntegerArrayListExtra("diagnoseTagList");
        CameraFragment cf = CameraFragment.newInstance(diagnoseTagList);
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

    @Override
    public void onStepComplete(Step step) {
        String title = step.getTitle();
        tbTvTitle.setText(title);
    }

    @Override
    public void onCaptureComplete(Step[] stepList) {

        for (Step step : stepList) {
            Step.stepMap.put(step.getTypeId(), step);
        }
//        Intent intent = new Intent(this, FileUploadActivity.class);
//        startActivity(intent);
//        finish();
        Intent intent = getIntent();
        intent.setClass(this, FileUploadActivity.class);
        startActivity(intent);
        finish();
    }
}
