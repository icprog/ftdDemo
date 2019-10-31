package com.william.ftd_core.runnable;

import com.william.ftd_core.exception.FtdException;

public interface BaseRunnableMethods<T> {

    void setCurrentThread(Thread thread);

    void onSuccess(T t);

    void onFail(FtdException e);

    void onComplete();
}
