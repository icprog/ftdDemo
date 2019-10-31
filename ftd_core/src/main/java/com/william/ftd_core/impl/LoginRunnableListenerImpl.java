package com.william.ftd_core.impl;

import com.william.ftd_core.call.LoginCallback;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.LoginRunnable;
import com.william.ftd_core.task.LoginTask;

public class LoginRunnableListenerImpl extends BaseImpl implements LoginRunnable.Listener {

    private LoginCallback callback;
    private LoginTask task;

    public LoginRunnableListenerImpl(LoginTask task, LoginCallback callback) {
        this.task = task;
        this.callback = callback;
    }

    @Override
    public String getPhone() {
        return task.getPhone();
    }

    @Override
    public String getCompanyCode() {
        return task.getCompanyCode();
    }

    @Override
    public String getAppId() {
        return task.getAppId();
    }

    @Override
    public void setCurrentThread(Thread thread) {
        task.setCurrentThread(thread);
    }

    @Override
    public void onSuccess(User user) {
        callback.onSuccess(user);
        task.setUser(user);
    }

    @Override
    public void onFail(FtdException e) {
        callback.onFail(e);
    }

    @Override
    public void onComplete() {
//        TaskManager.instance.handleState(task, TaskManager.TASK_COMPLETED);
    }
}
