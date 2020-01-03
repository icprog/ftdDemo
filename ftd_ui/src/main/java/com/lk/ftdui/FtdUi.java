package com.lk.ftdui;

import android.content.Context;
import android.util.Log;

import com.lk.ftd_core.TaskManager;
import com.lk.ftd_core.call.LoginCallback;
import com.lk.ftd_core.entity.User;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.activity.DoctorListActivity;

import io.reactivex.annotations.Nullable;

public class FtdUi {

    private static final String TAG = "FtdUi";

    /**
     * 登录面舌诊，成功后自动启动拍照页面
     *
     * @param phone
     * @param context
     */
    public static void login(final String phone, final Context context, final @Nullable FtdUILoginCallback callback) {
        TaskManager.instance.start(phone, new LoginCallback() {
            @Override
            public void onSuccess(User user) {
                if (callback != null) {
                    callback.onSuccess();
                }
                DoctorListActivity.start(context);
            }

            @Override
            public void onFail(FtdException e) {
                Log.e(TAG, "onFailed:登录面舌诊服务失败： ", e);
                if (callback != null) {
                    callback.onError(e);
                }
            }
        });
    }
}
