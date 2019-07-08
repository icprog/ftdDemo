package com.william.ftd_core.callback;

import com.william.ftd_core.entity.TendencyResult;

public interface FtdTendencyCallback extends BaseCallback {

    void onSuccess(TendencyResult result);
}
