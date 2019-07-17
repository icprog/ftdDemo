package com.william.ftd_core.callback;

import com.william.ftd_core.entity.Conclusion;

public interface FtdPicUploadCallback extends BaseCallback {
    void onSuccess(Conclusion result);
}
