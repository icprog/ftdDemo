package com.william.facetonguedoctor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.william.ftd_core.exception.FtdException;
import com.william.ftd_hybrid.FtdHybrid;
import com.william.ftdui.FtdUILoginCallback;
import com.william.ftdui.FtdUi;
import com.william.ftdui.widget.dialog.ConfirmationDialogFragment;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final String FRAGMENT_DIALOG = "dialog";

    private ProgressBar pb;
    private String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.pb = findViewById(R.id.pb);

        FtdUi.init(getApplicationContext());
    }

    public void goToFTD(View v) {
        EditText et = findViewById(R.id.et);
        mobile = et.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "请出入手机号！", Toast.LENGTH_SHORT).show();
        } else {
            start(mobile);
        }
    }

    public void goToFTDHybird(View v) {
        EditText et = findViewById(R.id.et);
        mobile = et.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "请出入手机号！", Toast.LENGTH_SHORT).show();
        } else {
            FtdHybrid.start(this, mobile);
        }
    }


    private void start(String mobile) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            pb.setVisibility(View.VISIBLE);
            pb.setEnabled(true);
            FtdUi.login(mobile, this, new FtdUILoginCallback() {
                @Override
                public void onSuccess() {
                    pb.setEnabled(false);
                    pb.setVisibility(View.GONE);
                }

                @Override
                public void onError(FtdException e) {
                    pb.setEnabled(false);
                    pb.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }
            });
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
            Toast.makeText(this, "请求权限失败。", Toast.LENGTH_SHORT).show();
            return;
        }
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "用户没有赋予相应权限。", Toast.LENGTH_SHORT).show();
            return;
        }
        start(mobile);
    }
}
