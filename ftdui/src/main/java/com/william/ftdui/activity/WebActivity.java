package com.william.ftdui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.william.ftdui.R;

public class WebActivity extends BaseActivity {

    private WebView wv;

    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {
        this.wv = findViewById(R.id.wv);
        setupWebView(wv);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        this.wv.loadUrl(url);
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

//    private static class MyWebChromeClient extends WebChromeClient {
//
//        private WebActivity mainActivity;
//
//        static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        static final int CAMERA_PERMISSION_REQUEST_CODE = 123;
//
//        MyWebChromeClient(WebActivity activity) {
//            this.mainActivity = activity;
//        }
//
//        @Override
//        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams
//                fileChooserParams) {
//            mainActivity.setUploadMessageAboveL(filePathCallback);
//
//            for (String requiredPermission : REQUIRED_PERMISSIONS) {
//                if (ContextCompat.checkSelfPermission(mainActivity, requiredPermission) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(mainActivity, REQUIRED_PERMISSIONS, CAMERA_PERMISSION_REQUEST_CODE);
//                    filePathCallback.onReceiveValue(null);
//                    return true;
//                }
//            }
//            //打开相机
//            this.mainActivity.getPicFromCamera();
//            return true;
//        }
//    }
}
