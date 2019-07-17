package com.william.ftdui;

import com.william.ftd_core.exception.FtdException;

public interface FtdUILoginCallback {

    void onSuccess();
    void onError(FtdException e);
}
