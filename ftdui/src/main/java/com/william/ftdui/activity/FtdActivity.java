package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.InitParam;
import com.william.ftdui.BuildConfig;
import com.william.ftdui.R;
import com.william.ftdui.fragment.CameraFragment;

import java.io.File;

public class FtdActivity extends BaseActivity {


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
}
