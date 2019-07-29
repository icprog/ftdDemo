package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.william.ftdui.R;
import com.william.ftdui.fragment.CameraFragment;

public class FtdActivity extends BaseActivity implements CameraFragment.OnCaptureCompleteListener {

    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;
    public static final int ASK = 3;

    @Override
    protected boolean setPBDefault() {
        return false;
    }


    @IntDef(value = {FACE, TONGUE_TOP, TONGUE_BOTTOM, ASK})
    @interface Step {
    }


    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {
        test();
    }

    @Override
    protected String setTitle() {
        return "拍照";
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_ftd;
    }

    private void test() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FtdActivity.this, ReportActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onCaptureComplete() {
        Intent intent = new Intent(this, FileUploadActivity.class);
        startActivity(intent);
        finish();
    }
}
