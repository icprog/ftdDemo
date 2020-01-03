package com.lk.facetonguedoctor;

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

import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.FtdUILoginCallback;
import com.lk.ftdui.FtdUi;
import com.lk.ftdui.activity.RecordListActivity;
import com.lk.ftdui.widget.dialog.ConfirmationDialogFragment;

import java.util.UUID;

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
        RecordListActivity.start(this);
    }


    private void start(String mobile) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            togglePb(true);
            FtdUi.login(mobile, this, new FtdUILoginCallback() {
                @Override
                public void onSuccess() {
                    togglePb(false);
                }

                @Override
                public void onError(FtdException e) {
                    togglePb(false);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ConfirmationDialogFragment
                    .newInstance(com.lk.ftdui.R.string.camera_permission_confirmation,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION,
                            com.lk.ftdui.R.string.camera_permission_not_granted)
                    .show(getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
        }
    }

    private void togglePb(final boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int visibility;
                if (b) {
                    visibility = View.VISIBLE;
                } else {
                    visibility = View.GONE;
                }
                pb.setEnabled(b);
                pb.setVisibility(visibility);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length < 1 || grantResults.length < 1) {
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
