package com.william.ftd_core.callback;

import com.william.ftd_core.entity.AnalyzeResultBean;

public interface FtdGetAnaylzerCallback extends BaseCallback {
    void onSuccess(AnalyzeResultBean bean);
}
