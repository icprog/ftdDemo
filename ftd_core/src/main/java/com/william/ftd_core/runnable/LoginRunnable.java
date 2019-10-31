package com.william.ftd_core.runnable;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.LoginParam;
import okhttp3.Response;

public class LoginRunnable implements Runnable {


    private Listener listener;

    public LoginRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        try {
            String token = getToken();
            login(token);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    /**
     * 获取令牌
     *
     * @return
     */
    private String getToken() throws FtdException {
        Response tokenResponse;
        String tokenResult;
        try {
            tokenResponse = ServerConnection.getInstance().getToken();
            tokenResult = ServerConnection.checkResponse(tokenResponse, String.class);
        } catch (Exception e) {
            throw new FtdException();
        }
        return tokenResult;
    }

    /**
     * 登陆
     *
     * @param token
     */
    private void login(String token) throws FtdException {
        try {
            LoginParam param = new LoginParam(listener.getPhone(), listener.getCompanyCode(), listener.getAppId(), token);
            Response loginResponse = ServerConnection.getInstance().login(param);
            User user = ServerConnection.checkResponse(loginResponse, User.class);
            ServerConnection.getInstance().updateUserToken(user.getUuid());
            listener.onSuccess(user);
        } catch (Exception e) {
            throw new FtdException();
        }
    }

    public interface Listener extends BaseRunnableMethods<User> {

        String getPhone();

        String getCompanyCode();

        String getAppId();
    }
}
