package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import okhttp3.Response;

public class QuestionRunnable implements Runnable {

    private Listener listener;

    public QuestionRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            Response response = ServerConnection.getInstance().getQuestion(listener.getUser(),listener.getSchemeId());
            AskBean result = ServerConnection.checkResponse(response, AskBean.class);
            listener.onSuccess(result);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    public interface Listener extends BaseRunnableMethods<AskBean> {
        User getUser();
        String getSchemeId();
    }
}
