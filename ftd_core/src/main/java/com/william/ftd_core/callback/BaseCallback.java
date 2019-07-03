package com.william.ftd_core.callback;

import com.william.ftd_core.exception.FtdException;

public interface BaseCallback {

    void onError(FtdException e);
}
