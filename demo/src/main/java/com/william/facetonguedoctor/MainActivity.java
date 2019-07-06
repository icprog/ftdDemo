package com.william.facetonguedoctor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.william.ftdui.FtdUi;
import com.william.ftdui.activity.ReportActivity;
import com.william.ftdui.widget.ConfirmationDialogFragment;
import com.william.ftdui.activity.FtdActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final String FRAGMENT_DIALOG = "dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FtdUi.init(getApplicationContext());
    }

    public void goToFTD(View v) {
        EditText et = findViewById(R.id.et);
        String mobile = et.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "请出入手机号！", Toast.LENGTH_SHORT).show();
        } else {
            start(mobile);
        }
    }


    private void start(String mobile) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            FtdUi.login(mobile, this);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            ConfirmationDialogFragment
                    .newInstance(com.william.ftdui.R.string.camera_permission_confirmation,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION,
                            com.william.ftdui.R.string.camera_permission_not_granted)
                    .show(getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length != 1 || grantResults.length != 1) {
            throw new RuntimeException("请求权限失败。");
        }
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "用户没有赋予相应权限。", Toast.LENGTH_SHORT).show();
        }
    }
}
