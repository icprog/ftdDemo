package com.william.ftd_core.call;

import com.william.ftd_core.entity.User;

public interface LoginCallback extends BaseCallback {
    void onSuccess(User user);
}
