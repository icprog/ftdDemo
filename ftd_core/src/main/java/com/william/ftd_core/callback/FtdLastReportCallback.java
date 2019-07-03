package com.william.ftd_core.callback;

import com.william.ftd_core.entity.ReportBean;

public interface FtdLastReportCallback extends BaseCallback {
    void onSuccess(ReportBean bean);
}
