package com.william.ftd_core.callback;

import com.william.ftd_core.entity.AskBean;

public interface FtdQuestionListCallback extends BaseCallback {
    void onSuccess(AskBean bean);
}
