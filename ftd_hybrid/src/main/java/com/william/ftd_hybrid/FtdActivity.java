package com.william.ftd_hybrid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.william.ftd_base.CameraFragment;
import com.william.ftd_base.constant.Step;

import java.util.ArrayList;

public class FtdActivity extends AppCompatActivity implements CameraFragment.OnCaptureCompleteListener {

    public static void getPicFromCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, FtdActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void getPicFromCamera(Fragment fragment,String[] stepIDs, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), FtdActivity.class);
        intent.putExtra("stepIDs",stepIDs);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ftd_h);

        Toolbar toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        TextView tbTvStart = findViewById(R.id.tb_tv_start);
        tbTvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int[] stepIDs = intent.getIntArrayExtra("stepIDs");
//        CameraFragment cf = CameraFragment.newInstance(stepIDs);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.container,cf);
//        ft.commit();
    }

    @Override
    public void onStepComplete(Step step) {

    }

    @Override
    public void onCaptureComplete(Step[] stepList) {
        Intent intent = new Intent();
        intent.putExtra("results", stepList);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
