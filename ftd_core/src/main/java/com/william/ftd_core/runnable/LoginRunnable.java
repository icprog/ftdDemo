package com.william.ftd_core.runnable;

import com.lk.mogaijson.JSON;
import com.lk.mogaijson.JSONObject;
import com.lk.mogaijson.TypeReference;
import com.william.ftd_core.BaseCallback;
import com.william.ftd_core.LoginTask;
import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.LoginParam;

import java.io.IOException;

import okhttp3.Response;

public class LoginRunnable implements Runnable {


    private LoginTask task;

    public LoginRunnable(LoginTask task) {
        this.task = task;
    }

    @Override
    public void run() {
        try {
            login(getToken());
        } catch (FtdException e) {
            task.onFail(e);
        }
    }

    /**
     * 初步检查网络请求结果
     *
     * @param response
     * @return
     * @throws IOException
     * @throws FtdException
     */
    private <T> T checkResponse(Response response, Class<T> clazz) throws IOException, FtdException {
        if (!response.isSuccessful()) {
            throw new FtdException();
        }
        String responseBody = response.body().string();
        FtdResponse ftdResponse = JSON.parseObject(responseBody, FtdResponse.class);
        if (ftdResponse.getCode() != 1000) {
            throw new FtdException(ftdResponse.getMsg());
        }
        if (
                clazz == byte.class ||
                        clazz == short.class ||
                        clazz == int.class ||
                        clazz == long.class ||
                        clazz == float.class ||
                        clazz == double.class ||
                        clazz == boolean.class ||
                        clazz == char.class ||
                        clazz == String.class
        ) {
            return (T) (ftdResponse.getData());
        } else {
            JSONObject jb = (JSONObject) ftdResponse.getData();
            return jb.toJavaObject(clazz);
        }
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
            tokenResult = checkResponse(tokenResponse, String.class);
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
            LoginParam param = new LoginParam(task.getPhone(), task.getCompanyCode(), task.getAppId(), token);
            Response loginResponse = ServerConnection.getInstance().login(param);
            User user = checkResponse(loginResponse, User.class);
            task.onSuccess(user);
        } catch (Exception e) {
            throw new FtdException();
        }
    }

    public interface LoginCallback extends BaseCallback {
        void onSuccess(User user);
    }
}
