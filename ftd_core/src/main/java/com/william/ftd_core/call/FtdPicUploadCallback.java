package com.william.ftd_core.call;

import com.william.ftd_core.callback.BaseCallback;
import com.william.ftd_core.param.DiagnoseParam;

public interface FtdPicUploadCallback extends BaseCallback {
    void onSucceed(DiagnoseParam diagnoseParam);

    void onFailed(DiagnoseParam diagnoseParam);
}
