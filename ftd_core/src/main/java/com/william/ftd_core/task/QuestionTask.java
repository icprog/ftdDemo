package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.callback.FtdQuestionListCallback;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.QuestionRunnable;

import java.lang.ref.WeakReference;

public class QuestionTask extends FtdTask<FtdQuestionListCallback> implements QuestionRunnable.Listener {


    private User user;
    private String schemeId;
    private WeakReference<FtdQuestionListCallback> weakCallback;

    public QuestionTask(User user, String schemeId, FtdQuestionListCallback callback) {
        this.user = user;
        this.schemeId = schemeId;
        this.weakCallback = new WeakReference<>(callback);
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }

    public void getQuestionList(){
        QuestionRunnable runnabel = new QuestionRunnable(this);
        TaskManager.instance.threadPool.execute(runnabel);
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getSchemeId() {
        return schemeId;
    }

    @Override
    public void onSuccess(AskBean askBean) {
        FtdQuestionListCallback callback = weakCallback.get();
        if (callback != null) {
            callback.onSuccess(askBean);
        }
    }

    @Override
    public void onFail(FtdException e) {
        FtdQuestionListCallback callback = weakCallback.get();
        if (null != callback) {
            callback.onError(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
