package com.william.ftd_core.runnable;

import com.lk.mogaijson.JSON;
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
            String token = getToken();
            login( token);
        } catch (FtdException e) {
            task.onError(e);
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
    private <T> T checkResponse(Response response) throws IOException, FtdException {
        if (response.isSuccessful() && response.body() != null) {
            String tokenResult = response.body().string();
            FtdResponse<T> ftdResponse = JSON.parseObject(tokenResult, new TypeReference<FtdResponse<T>>() {
            });
            if (ftdResponse.getCode() == 1000 && ftdResponse.getData() != null) {
                return ftdResponse.getData();
            } else {
                throw new FtdException(ftdResponse.getMsg());
            }
        } else {
            throw new FtdException();
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
            tokenResult = checkResponse(tokenResponse);
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
    private void login( String token) throws FtdException {
        try {
            LoginParam param = new LoginParam(task.getPhone(), task.getCompanyCode(), task.getAppId(), token);
            Response loginResponse = ServerConnection.getInstance().login(param);
            User user = checkResponse(loginResponse);
            task.onSuccess(user);
        } catch (Exception e) {
            throw new FtdException();
        }
    }

    public interface LoginCallback extends BaseCallback {
        void onSuccess(User user);
    }
}
