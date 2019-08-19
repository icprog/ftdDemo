package com.william.ftd_hybrid;

import android.webkit.JavascriptInterface;

public class FileChooser {

    private OnFileChooserListener listener;

    public FileChooser(OnFileChooserListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void capture() {
        listener.getPicFromCamera();
    }

    public void onCaptureComplete(String fileData) {
        listener.onCaptureComplete(fileData);
    }

    interface OnFileChooserListener {
        void getPicFromCamera();

        void onCaptureComplete(String fileData);
    }
}
