package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import java.util.List;

import okhttp3.Response;

public class AnswerRunnable implements Runnable {
    private Listener listener;

    public AnswerRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            Response response = ServerConnection.getInstance().submitAnswer(
                    listener.getUser(),
                    listener.getCardQuestion(),
                    listener.getQuestion(),
                    listener.getTraceId(),
                    listener.getTraceId1(),
                    listener.getSchemeId()
            );
            AskBean result = ServerConnection.checkResponse(response, AskBean.class);
            listener.onSuccess(result);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    //    User user,List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2,String schemeId
    public interface Listener extends BaseRunnableMethods<AskBean> {
        User getUser();

        List<QuestionBean> getCardQuestion();
        List<QuestionBean> getQuestion();

        String getTraceId();
        String getTraceId1();
        String getSchemeId();
    }
}
