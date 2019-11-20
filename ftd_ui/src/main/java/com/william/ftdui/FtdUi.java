package com.william.ftdui;

import android.content.Context;
import android.util.Log;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.call.LoginCallback;
import com.william.ftd_core.constant.Constant1;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.activity.FtdActivity;

import java.util.ArrayList;

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
                ArrayList<Integer> diagnoseTagList = new ArrayList<>(3);
                diagnoseTagList.add(Constant1.FACE);
                diagnoseTagList.add(Constant1.TONGUE_TOP);
                diagnoseTagList.add(Constant1.TONGUE_BOTTOM);
                FtdActivity.getPicFromCamera(context, diagnoseTagList);
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
