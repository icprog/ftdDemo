package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.call.LoginCallback;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.impl.LoginRunnableListenerImpl;
import com.william.ftd_core.runnable.LoginRunnable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class LoginTask extends FtdTask<LoginCallback> {

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


    public void setUser(User user) {
        TaskManager.instance.setUser(user);
    }

    private ArrayList<WeakReference> uiList = new ArrayList<>(10);


    public LoginTask(final String phone) {
        this.phone = phone;
    }

    /**
     * 登陆
     *
     * @param callback
     */
    public void login(final LoginCallback callback) {
        weakCallback = new WeakReference<>(callback);
        uiList.add(weakCallback);
        LoginRunnable.Listener listener = new LoginRunnableListenerImpl(this, weakCallback.get());
        TaskManager.instance.threadPool.execute(new LoginRunnable(listener));
    }


    @Override
    public Runnable getRunnable() {
        return runnable;
    }

}
