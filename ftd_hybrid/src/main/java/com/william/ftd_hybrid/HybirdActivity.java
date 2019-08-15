package com.william.ftd_hybrid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class HybirdActivity extends AppCompatActivity {

    public static final String TAG = "FTD_HYBRID";

    private Toolbar toolBar;

    private String appId;
    private String companyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybird);
        this.toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            appId = String.valueOf(appInfo.metaData.getInt("laiKang.appId"));
            companyCode = appInfo.metaData.getString("laiKang.companyCode");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "init: ", e);
            return;
        }

        HybirdFragment fragment = HybirdFragment.newInstance(appId, companyCode, mobile);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }

    public static void start(Context context, String mobile) {
        Intent intent = new Intent(context, HybirdActivity.class);
        intent.putExtra("mobile", mobile);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
