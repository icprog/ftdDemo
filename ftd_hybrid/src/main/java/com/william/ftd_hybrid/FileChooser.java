package com.william.ftd_hybrid;

import android.support.v4.util.ArrayMap;
import android.webkit.JavascriptInterface;

import com.william.ftd_base.constant.Constant;
import com.william.ftd_base.constant.Step;

public class FileChooser {

    private OnFileChooserListener listener;

    public FileChooser(OnFileChooserListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void capture(String[] stepIds) {
        listener.getPicFromCamera(stepIds);
    }

    public void onCaptureComplete(String fileData) {
        listener.onCaptureComplete(fileData);
    }

    interface OnFileChooserListener {
        void getPicFromCamera(String[] stepIds);

        void onCaptureComplete(String fileData);
    }
}
