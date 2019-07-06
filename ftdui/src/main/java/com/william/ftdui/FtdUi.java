package com.william.ftdui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.InitParam;
import com.william.ftdui.activity.FtdActivity;

public class FtdUi {

    private static final String TAG = "FtdUi";

    /**
     * 初始化面舌诊环境
     * @param context
     */
    public static void init(Context context) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "init: ", e);
            return;
        }

        String appId = String.valueOf(appInfo.metaData.getInt("laiKang.appId"));
        String appCode = String.valueOf(appInfo.metaData.getInt("laiKang.appCode"));
        String appKey = appInfo.metaData.getString("laiKang.appKey");
        String appSecret = appInfo.metaData.getString("laiKang.appSecret");
        String companyId = String.valueOf(appInfo.metaData.getInt("laiKang.companyId"));
        String companyPid = String.valueOf(appInfo.metaData.getInt("laiKang.companyPid"));
        String companyCode = appInfo.metaData.getString("laiKang.companyCode");
        String phrAppKey = appInfo.metaData.getString("laiKang.phrAppKey");
        String phrAppSecret = appInfo.metaData.getString("laiKang.phrAppSecret");


        InitParam param = new InitParam(
                appId,
                appCode,
                appKey,
                appSecret,
                companyId,
                companyPid,
                companyCode,
                phrAppKey,
                phrAppSecret
        );

        FtdClient.getInstance().init(param);
    }

    /**
     * 登录面舌诊，成功后自动启动拍照页面
     * @param phone
     * @param context
     */
    public static void login(final String phone, final Context context) {
        FtdClient.getInstance().login(phone, new FtdLoginCallback() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent();
                intent.setClass(context, FtdActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onError(FtdException e) {
                Log.e(TAG, "onError:登录面舌诊服务失败： ", e);
            }
        });
    }
}
