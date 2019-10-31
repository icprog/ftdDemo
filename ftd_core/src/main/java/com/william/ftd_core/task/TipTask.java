package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.call.FtdMicroTipCallback;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.TipRunnable;

import java.lang.ref.WeakReference;

public class TipTask extends FtdTask implements TipRunnable.Listener {

    private User user;
    private WeakReference<FtdMicroTipCallback> weakCallback;

    public TipTask(User user,FtdMicroTipCallback callback) {
        this.user = user;
        this.weakCallback = new WeakReference<>(callback);
    }

    @Override
    public Runnable getRunnable() {
        return null;
    }


    public void getTip(){
        TipRunnable runnable = new TipRunnable(this);
        TaskManager.instance.threadPool.execute(runnable);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void onSuccess(MicroTipBean microTipBean) {
        FtdMicroTipCallback callback = weakCallback.get();
        callback.onSuccess(microTipBean);

    }

    @Override
    public void onFail(FtdException e) {
        FtdMicroTipCallback callback = weakCallback.get();
        callback.onError(e);
    }

    @Override
    public void onComplete() {
        FtdMicroTipCallback callback = weakCallback.get();
//        callback.onComplete();
    }
}
