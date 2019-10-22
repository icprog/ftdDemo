package com.william.ftd_core;

import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.LoginRunnable;

import java.lang.ref.WeakReference;

public class LoginTask implements FtdTask, LoginRunnable.LoginCallback {

    private String phone, companyCode, appId;

//    private LoginRunnable.LoginCallback callback;
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


    @Override
    public void onSuccess(User user) {
//        int i = 0;
        callback.get().onSuccess(user);
    }

    @Override
    public void onFail(FtdException e) {
//        int i = 0;
        callback.get().onFail(e);
    }
}
