package com.william.facetonguedoctor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.william.ftdui.CameraFragment;
import com.william.ftdui.FtdActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }
    public void goToFTD(View v){
        start();
    }

    private void start(){
        Intent intent = getIntent();
        intent.setClass(this, FtdActivity.class);
        startActivity(intent);
    }
}
