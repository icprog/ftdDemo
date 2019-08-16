package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.william.ftdui.R;
import com.william.ftdui.constant.Constant;
import com.william.ftdui.constant.Step;
import com.william.ftdui.fragment.CameraFragment;
import java.util.ArrayList;

public class FtdActivity extends BaseActivity implements CameraFragment.OnCaptureCompleteListener {

    @Override
    protected boolean setPBDefault() {
        return false;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        ArrayList<Step> stepList = intent.getParcelableArrayListExtra("stepList");
        if (stepList == null){
            stepList = new ArrayList<>(10);
            stepList.add(new Step(Constant.STEP_FACE));
            stepList.add(new Step(Constant.STEP_TONGUE_TOP));
            stepList.add(new Step(Constant.STEP_TONGUE_BOTTOM));
        }

        CameraFragment cf = CameraFragment.newInstance(stepList);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,cf);
        ft.commit();
    }

    @Override
    protected String setTitle() {
        return "拍照";
    }

    @Override
    public int setContentViewResId() {
        return R.layout.activity_ftd;
    }

    @Override
    public void onCaptureComplete() {
        Intent intent = new Intent(this, FileUploadActivity.class);
        startActivity(intent);
        finish();
    }
}
