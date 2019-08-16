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
import com.william.ftd_hybrid.constant.Constant;
import com.william.ftd_hybrid.constant.Step;
import java.util.ArrayList;

public class FtdActivity extends AppCompatActivity implements CameraFragment.OnCaptureCompleteListener {

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
    public void onCaptureComplete(String[] paths) {
        Intent intent = new Intent();
        intent.putExtra("paths", paths);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
