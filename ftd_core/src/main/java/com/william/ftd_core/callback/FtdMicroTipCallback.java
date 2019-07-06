package com.william.ftd_core.callback;

import com.william.ftd_core.entity.MicroTipBean;

public interface FtdMicroTipCallback extends BaseCallback {
    void onSuccess(MicroTipBean bean);
}
