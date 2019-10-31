package com.william.ftd_core.impl;

import com.william.ftd_core.call.FtdPicUploadCallback;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.DiagnoseParam;
import com.william.ftd_core.runnable.AnalyzeRunnable;
import com.william.ftd_core.task.UploadTask;

public class AnalyzeRunnableListenerImpl extends BaseImpl implements AnalyzeRunnable.Listener {

    private UploadTask task;
    private DiagnoseParam param;


    public AnalyzeRunnableListenerImpl(UploadTask task, DiagnoseParam diagnoseParam) {
        this.task = task;
        this.param = diagnoseParam;
    }

    @Override
    public DiagnoseParam getParam() {
        return param;
    }

    @Override
    public void setCurrentThread(Thread thread) {
        task.setCurrentThread(thread);
    }

    @Override
    public void onSuccess(UploadResult uploadResult) {
        this.param.setResult(uploadResult);
        task.analyzeSuccess(param);
    }

    @Override
    public void onFail(FtdException e) {
        this.param.setException(e);
        task.analyzeFaild(param);
    }

    @Override
    public void onComplete() {

    }
}
