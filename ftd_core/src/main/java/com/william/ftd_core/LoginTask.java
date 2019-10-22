package com.william.ftd_core;

import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.LoginRunnable;

import java.lang.ref.WeakReference;

public class LoginTask implements FtdTask {

    private String phone, companyCode, appId;

    private WeakReference<LoginRunnable.LoginCallback> callback;

    public String getPhone() {
        return phone;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getAppId() {
        return appId;
    }

    private Runnable runnable;

    public LoginTask(String phone,LoginRunnable.LoginCallback callback) {
        this.phone = phone;
        this.callback = new WeakReference<>(callback);

        runnable = new LoginRunnable(this);
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }


    public void onSuccess(User user) {
        callback.get().onSuccess(user);
    }

    public void onFail(FtdException e) {
        callback.get().onFail(e);
    }
}
