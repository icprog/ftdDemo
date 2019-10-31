package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.callback.FtdSubmitCallback;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.AnswerRunnable;
import java.lang.ref.WeakReference;
import java.util.List;

public class AnswerTask extends FtdTask implements AnswerRunnable.Listener {

    private User user;
    private WeakReference<FtdSubmitCallback> weakCallback;
    List<QuestionBean> questionList1;
    List<QuestionBean> questionList2;
    String traceId1;
    String traceId2;
    String schemeId;

    public AnswerTask(User user, List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2, String schemeId, FtdSubmitCallback callback) {
        this.user = user;
        this.questionList1 = questionList1;
        this.questionList2 = questionList2;
        this.traceId1 = traceId1;
        this.traceId2 = traceId2;
        this.weakCallback = new WeakReference<>(callback);
        this.schemeId = schemeId;
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }

    public void submitAnswer() {
        AnswerRunnable runnable = new AnswerRunnable(this);
        TaskManager.instance.threadPool.execute(runnable);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public List<QuestionBean> getCardQuestion() {
        return questionList1;
    }

    @Override
    public List<QuestionBean> getQuestion() {
        return questionList2;
    }

    @Override
    public String getTraceId() {
        return traceId1;
    }

    @Override
    public String getTraceId1() {
        return traceId2;
    }

    @Override
    public String getSchemeId() {
        return schemeId;
    }

    @Override
    public void onSuccess(AskBean askBean) {
        FtdSubmitCallback callback = this.weakCallback.get();
        callback.onSuccess(askBean);
    }

    @Override
    public void onFail(FtdException e) {
        FtdSubmitCallback callback = this.weakCallback.get();
        callback.onError(e);
    }

    @Override
    public void onComplete() {
    }
}
