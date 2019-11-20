package com.william.ftd_core.task;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.william.ftd_core.TaskManager;
import com.william.ftd_core.Util;
import com.william.ftd_core.call.FtdPicUploadCallback;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.impl.AnalyzeRunnableListenerImpl;
import com.william.ftd_core.param.DiagnoseParam;
import com.william.ftd_core.runnable.AnalyzeRunnable;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;


/**
 * 处理图片上传分析
 */
public class UploadTask extends FtdTask<FtdPicUploadCallback> {

    private String TAG = "UploadTask";

    private User user;

    private SparseArray<DiagnoseParam> diagnoseParams;

//    private WeakReference<FtdPicUploadCallback> weakCallback;

    public void setUser(User user) {
        this.user = user;
    }


    public UploadTask(User user, @NonNull SparseArray<DiagnoseParam> diagnoseParams, final FtdPicUploadCallback callback) {
        this.user = user;
        this.diagnoseParams = diagnoseParams;
        weakCallback = new WeakReference<>(callback);
    }


    /**
     * 分析
     *
     */
    public void analyze(String schemeId) {
        int size = diagnoseParams.size();
        for (int i = 0; i < size; i++) {
            DiagnoseParam param = diagnoseParams.valueAt(i);
            param.setScheme_flag(schemeId);
            param.setUser(user);
            AnalyzeRunnable.Listener listener = new AnalyzeRunnableListenerImpl(this, param);
            TaskManager.instance.threadPool.execute(new AnalyzeRunnable(listener));
        }
    }

    private int time;

    public synchronized void analyzeSuccess(DiagnoseParam diagnoseResult) {
        FtdPicUploadCallback callback = weakCallback.get();
        if (callback != null) {
            callback.onSucceed(diagnoseResult);
        } else {
            Log.e(TAG, "analyzeSuccess: 为null" );
        }
        complete();
    }

    public synchronized void analyzeFaild(DiagnoseParam diagnoseResult) {
        FtdPicUploadCallback callback = weakCallback.get();
        if (callback != null) {
            callback.onFailed(diagnoseResult);
        } else {
            Log.e(TAG, "analyzeFaild: 为null" );
        }
        complete();
    }

    private void complete() {
        ++time;
        if (time == diagnoseParams.size()) {
            TaskManager.instance.handleState(this, TaskManager.TASK_COMPLETED);
        }
    }


    @Override
    public Runnable getRunnable() {
        return runnable;
    }
}
