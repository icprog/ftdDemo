package com.william.ftd_core;

import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.runnable.LoginRunnable;

public class LoginTask implements FtdTask, LoginRunnable.LoginCallback {

    private String phone, companyCode, appId;

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

    public LoginTask(String phone) {
        this.phone = phone;

        runnable = new LoginRunnable(this);
    }

    @Override
    public Runnable getRunnable() {
        return runnable;
    }


    @Override
    public void onSuccess(User user) {
        int i = 0;
    }

    @Override
    public void onFail(FtdException e) {
        int i = 0;
    }
}
