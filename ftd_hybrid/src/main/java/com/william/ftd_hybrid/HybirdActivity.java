package com.william.ftd_hybrid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class HybirdActivity extends AppCompatActivity {

    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hybird);
        this.toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);

        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");

        HybirdFragment fragment = HybirdFragment.newInstance(mobile);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
    }

    public static void start(Context context, String mobile){
        Intent intent = new Intent(context, HybirdActivity.class);
        intent.putExtra("mobile",mobile);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
