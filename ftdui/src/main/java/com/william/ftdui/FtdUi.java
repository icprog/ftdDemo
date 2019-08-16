package com.william.ftdui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.activity.FtdActivity;

import retrofit2.Converter;

public class FtdUi {

    private static final String TAG = "FtdUi";

    /**
     * 初始化面舌诊环境
     *
     * @param context
     */
    public static void init(Context context, Converter.Factory retrofitFactory, FtdClient.JsonConverter jsonConverter) {
        FtdClient.getInstance().init(context,retrofitFactory,jsonConverter);

    }

    /**
     * 登录面舌诊，成功后自动启动拍照页面
     *
     * @param phone
     * @param context
     */
    public static void login(final String phone, final Context context, final FtdUILoginCallback callback) {
        FtdClient.getInstance().login(phone, new FtdLoginCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onSuccess();
                }
                Intent intent = new Intent();
                intent.setClass(context, FtdActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onError(FtdException e) {
                Log.e(TAG, "onError:登录面舌诊服务失败： ", e);
                if (callback != null) {
                    callback.onError( e);
                }
            }
        });
    }
}
