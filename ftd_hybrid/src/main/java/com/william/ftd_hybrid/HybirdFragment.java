package com.william.ftd_hybrid;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.william.ftd_base.FtdResult;
import com.william.ftd_base.constant.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;


public class HybirdFragment extends Fragment implements FileChooser.OnFileChooserListener {

    private static final String TAG = "HybirdFragment";

    private static final String urlReg = "%smsz/index?companyCode=%s&AppId=%s&sourceType=shixiu&userTel=%s";
    private String url;
    private WebView wv;
    private ProgressBar pb;
    private ValueCallback<Uri[]> uploadMessageAboveL;

    public static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;

    private FileChooser fileChooser = new FileChooser(this);

    private String[] stepIds;

    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private Handler mMainThreadHandler = new MainThreadHandler(this);


    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            mBackgroundThread = new HandlerThread("background");
            mBackgroundThread.start();
            mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
        }
        return mBackgroundHandler;
    }

    public static HybirdFragment newInstance(String appId, String companyCode, String mobile) {
        HybirdFragment fragment = new HybirdFragment();
        Bundle b = new Bundle();
        b.putString("appId", appId);
        b.putString("companyCode", companyCode);
        b.putString("mobile", mobile);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        String mobile = b.getString("mobile");
        String appId = b.getString("appId");
        String companyCode = b.getString("companyCode");
        url = String.format(urlReg, BuildConfig.HOST, companyCode, appId, mobile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hybird, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wv = getView().findViewById(R.id.wv);
        pb = getView().findViewById(R.id.pb);
        setupWebView(wv);
        wv.loadUrl(url);
        getBackgroundHandler();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBackgroundThread == null) {
            return;
        }
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            Log.e(TAG, "close: 后台线程关闭失败：", e);
        }
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
        settings.setSupportZoom(false);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        this.wv.setWebViewClient(new WebViewClient() {
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
        this.wv.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams
                    fileChooserParams) {
                uploadMessageAboveL = filePathCallback;

                getPicFromCamera(new String[]{Constant.STEP_FACE, Constant.STEP_TONGUE_TOP, Constant.STEP_TONGUE_BOTTOM});

                return true;
            }


        });

        this.wv.addJavascriptInterface(fileChooser, "laikang");
    }

    /**
     * 进入拍照页面
     */
    @Override
    public void getPicFromCamera(String[] stepIds) {
        this.stepIds = stepIds;
        for (String requiredPermission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(getContext(), requiredPermission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(REQUIRED_PERMISSIONS, CAMERA_PERMISSION_REQUEST_CODE);
                uploadMessageAboveL.onReceiveValue(null);
                return;
            }
        }
        //打开相机
        FtdActivity.getPicFromCamera(HybirdFragment.this, stepIds, CAMERA_REQUEST_CODE);
    }

    /**
     * 拍照完成回掉
     */
    @Override
    public void onCaptureComplete(String fileDatas) {
        String jsReg = "onCaptureComplete(%s)";
        String jsMethod = String.format(jsReg, fileDatas);
        wv.evaluateJavascript(jsMethod, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            FtdActivity.getPicFromCamera(this, stepIds, CAMERA_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri[] uris = null;
        FtdResult[] results = null;
        File[] files = null;
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Parcelable[] resultData = data.getParcelableArrayExtra("results");
            results = Arrays.copyOf(resultData, resultData.length, FtdResult[].class);
            uris = new Uri[results.length];
            files = new File[results.length];
            for (int i = 0; i < results.length; i++) {
                files[i] = new File(results[i].getPath());
                uris[i] = Uri.fromFile(files[i]);
            }
            send(files, results);
        }
        if (uploadMessageAboveL != null) {
            uploadMessageAboveL.onReceiveValue(uris);
            uploadMessageAboveL = null;
        }
    }

    /**
     * 拍照结果异步序列化成字符串
     *
     * @param files
     * @param results
     */
    private void send(final File[] files, final FtdResult[] results) {
        mBackgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject;
                try {
                    for (int i = 0; i < results.length; i++) {
                        results[i].setImgRes(trans(files[i]));
                        jsonObject = new JSONObject();
                        jsonObject.put("stepId", results[i].getStepId());
                        jsonObject.put("imgRes", results[i].getImgRes());
                        jsonArray.put(jsonObject);
                    }
                    Message msg = mMainThreadHandler.obtainMessage();
                    msg.obj = jsonArray.toString();
                    mMainThreadHandler.sendMessage(msg);
                } catch (JSONException e) {
                    Log.e(TAG, "send()方法报错: ", e);
                }
            }
        });
    }

    /**
     * 文件序列化，并且Base64编码
     *
     * @param file
     * @return
     */
    private String trans(File file) {
        StringBuffer fileDataBuffer = new StringBuffer("data:image/jpeg;base64,");
        FileInputStream fis = null;
        ByteArrayOutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            byte[] bytes = os.toByteArray();
            String dataBase64 = Base64.encodeToString(bytes, Base64.NO_WRAP);
            return fileDataBuffer.append(dataBase64).toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 展示进度条
     */
    protected void showProgress() {
        if (pb != null && !pb.isEnabled()) {
            pb.setEnabled(true);
            pb.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏进度条（不占位）
     */
    protected void hideProgress() {
        if (pb != null && pb.isEnabled()) {
            pb.setVisibility(View.GONE);
            pb.setEnabled(false);
        }
    }

    /**
     * 浏览器回退
     *
     * @return
     */
    public boolean goback() {
        if (wv.canGoBack()) {
            wv.goBack();
            return true;
        } else {
            return false;
        }
    }

    private static class MainThreadHandler extends Handler {
        private WeakReference<HybirdFragment> fragment;

        public MainThreadHandler(HybirdFragment fragment) {
            this.fragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String result = (String) msg.obj;
            this.fragment.get().fileChooser.onCaptureComplete(result);
        }
    }
}
