package com.lk.ftdui;

import com.lk.ftd_core.exception.FtdException;

public interface FtdUILoginCallback {

    void onSuccess();
    void onError(FtdException e);
}
