package com.william.ftd_core;

import com.william.ftd_core.exception.FtdException;

public interface BaseCallback {

    void onFail(FtdException e);
}
