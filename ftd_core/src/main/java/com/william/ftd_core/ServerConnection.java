package com.william.ftd_core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.lk.mogaijson.JSON;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.param.HeaderParam;
import com.william.ftd_core.param.InitParam;
import com.william.ftd_core.param.LoginParam;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Converter;

public class ServerConnection {

    private static final String TAG = ServerConnection.class.getSimpleName();
    private HeaderParam headerParam;

    public ServerConnection() {

    }


    /**
     * 初始化方法二（推荐）
     *
     * @param headerParam
     */
    public void init(HeaderParam headerParam) {
        this.headerParam = headerParam;
    }

    public static ServerConnection getInstance() {
        return Handler.instance;
    }

    private static class Handler {
        private static ServerConnection instance = new ServerConnection();
    }

    private OkHttpClient serviceClient = new OkHttpClient.Builder()
            .build();

    private OkHttpClient fileClient = new OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .build();

    private Request createRequestBuilder(String path, RequestBody... requestBodies) {
        String reg = "https://qa.laikangland.com/%s";
        String url = String.format(reg, path);
        Request.Builder builder = new Request.Builder()
                .header(ServiceApi.APP_ID, headerParam.appId)
                .header(ServiceApi.APP_CODE, headerParam.appCode)
                .header(ServiceApi.PLACE_COMPANY_ID, headerParam.companyId)
                .header(ServiceApi.PLACE_COMPANY_P_ID, headerParam.companyPid)
                .header(ServiceApi.COMPANY_CODE, headerParam.companyCode)
                .header(ServiceApi.App_KEY, headerParam.appKey)
                .header(ServiceApi.LK_TOKEN, headerParam.lkToken)
                .url(url);
        if (requestBodies.length>0){
            builder.post(requestBodies[0]);
        }
        return builder.build();
    }
    private RequestBody createRequestBody(String json) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

    /**
     * 获取令牌
     *
     * @return
     */
    public Response getToken() throws IOException {
        Request request = createRequestBuilder(ServiceApi.GET_THIRD_TOKEN);
        Call call = serviceClient.newCall(request);
        return call.execute();
    }

    /**
     * 登陆
     *
     * @return
     */
    public Response login(LoginParam param) throws IOException {
        String json = JSON.toJSONString(param);
        RequestBody requestBody = createRequestBody(json);
        Request request = createRequestBuilder(ServiceApi.LOGIN,requestBody);
        return serviceClient.newCall(request).execute();
    }
}
