package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import okhttp3.Response;

public class RecordRunnable implements Runnable {


    private Listener listener;

    public RecordRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            Response response = ServerConnection.getInstance().getRecoedBySeqNO(listener.getUser(),listener.getSeqNO());
            ReportBean result = ServerConnection.checkResponse(response, ReportBean.class);
            listener.onSuccess(result);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    public interface Listener extends BaseRunnableMethods<ReportBean> {
        User getUser();
        long getSeqNO();
    }
}
