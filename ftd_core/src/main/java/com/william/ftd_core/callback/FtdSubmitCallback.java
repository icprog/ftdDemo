package com.william.ftd_core.callback;

import com.william.ftd_core.entity.AskBean;

public interface FtdSubmitCallback extends BaseCallback {
    void onSuccess(AskBean bean);
}
