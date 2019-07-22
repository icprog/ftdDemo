package com.william.ftdui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.william.ftdui.R;

public class WebActivity extends BaseActivity implements View.OnClickListener {

    private WebView wv;

    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {
        this.wv = findViewById(R.id.wv);
        setupWebView(wv);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        this.wv.loadUrl(url);


        findViewById(R.id.btn_back).setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_web;
    }

    /**
     * 对WebView的必须设置
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView(WebView wv) {
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
//        settings.setTextZoom(100);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
//        this.wv.setWebChromeClient(new MyWebChromeClient(this));
        this.wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgress();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back) {
            if (wv.canGoBack()){
                wv.goBack();
                return;
            } else {
                finish();
            }
        }
    }
}
