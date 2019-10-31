package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import okhttp3.Response;

public class TipRunnable implements Runnable {

    private Listener listener;

    public TipRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            Response response = ServerConnection.getInstance().getTip(listener.getUser());
            MicroTipBean result = ServerConnection.checkResponse(response, MicroTipBean.class);
            listener.onSuccess(result);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    public interface Listener extends BaseRunnableMethods<MicroTipBean> {
        User getUser();
    }
}
