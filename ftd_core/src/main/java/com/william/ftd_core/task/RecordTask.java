package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.RecordRunnable;

import java.lang.ref.WeakReference;

public class RecordTask extends FtdTask implements RecordRunnable.Listener {

    private User user;
    private long seqNO;
    private WeakReference<FtdLastReportCallback> weakCallback;

    public RecordTask(User user, long seqNO,FtdLastReportCallback callback) {
        this.user = user;
        this.seqNO = seqNO;
        this.weakCallback = new WeakReference<>(callback);
    }

    @Override
    public Runnable getRunnable() {
        return null;
    }

    public void getRecord(){
        RecordRunnable runnable = new RecordRunnable(this);
        TaskManager.instance.threadPool.execute(runnable);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public long getSeqNO() {
        return seqNO;
    }

    @Override
    public void onSuccess(ReportBean reportBean) {
        FtdLastReportCallback callback = weakCallback.get();
        callback.onSuccess(reportBean);
    }

    @Override
    public void onFail(FtdException e) {
        FtdLastReportCallback callback = weakCallback.get();
        callback.onError(e);
    }

    @Override
    public void onComplete() {

    }
}
