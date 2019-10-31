package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.DiagnoseParam;

import okhttp3.Response;

public class AnalyzeRunnable implements Runnable {

    private Listener listener;

    public AnalyzeRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            Response response = ServerConnection.getInstance().upload(listener.getParam());
            UploadResult result = ServerConnection.checkResponse(response, UploadResult.class);
            listener.onSuccess(result);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    public interface Listener extends BaseRunnableMethods<UploadResult> {
        DiagnoseParam getParam();
    }
}
