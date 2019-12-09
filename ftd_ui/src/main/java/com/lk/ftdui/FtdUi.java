package com.lk.ftdui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lk.ftd_base.constant.Constant;
import com.lk.ftd_core.FtdClient;
import com.lk.ftd_core.callback.FtdLoginCallback;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.activity.FtdActivity;

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
                FtdActivity.getPicFromCamera(context,new String[]{Constant.STEP_FACE,Constant.STEP_TONGUE_TOP,Constant.STEP_TONGUE_BOTTOM});
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
