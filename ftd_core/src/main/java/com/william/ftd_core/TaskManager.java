package com.william.ftd_core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;

import com.william.ftd_core.call.FtdMicroTipCallback;
import com.william.ftd_core.call.FtdPicUploadCallback;
import com.william.ftd_core.call.LoginCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdQuestionListCallback;
import com.william.ftd_core.callback.FtdSubmitCallback;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.DiagnoseParam;
import com.william.ftd_core.task.AnswerTask;
import com.william.ftd_core.task.FtdTask;
import com.william.ftd_core.task.LoginTask;
import com.william.ftd_core.task.QuestionTask;
import com.william.ftd_core.task.RecordTask;
import com.william.ftd_core.task.TipTask;
import com.william.ftd_core.task.UploadTask;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 负责创建和回收Task,管理Task的生命周期
 */
public class TaskManager {

    public static TaskManager instance = new TaskManager();

    /**
     * cpu核心数
     */
//    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static final int KEEP_ALIVE_TIME = 1;
    private static TimeUnit KEEP_ALIVE_TIME_UNIT;
    private static final int CORE_POOL_SIZE = 8;
    private static final int MAXIMUM_POOL_SIZE = 8;
    private final BlockingQueue<Runnable> workQueue;
    private final Queue<FtdTask> taskQueue;


    public ThreadPoolExecutor threadPool;

    public static final int TASK_STARTED = 0;
    public static final int TASK_SUCCEED = 1;
    public static final int TASK_FAILED = -1;
    public static final int TASK_CANCELED = -2;
    public static final int TASK_COMPLETED = 3;

    private User user;

    private String schemeId;

    private void cleanSchemeId() {
        schemeId = null;
    }

    public void setUser(User user) {
        synchronized (instance) {
            instance.user = user;
        }
    }

    private Handler mainHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TASK_STARTED:

                    break;
                case TASK_SUCCEED:

                    break;
                case TASK_FAILED:

                    break;
                case TASK_CANCELED:
                    break;
                default://TASK_COMPLETED
                    FtdTask task = (FtdTask) msg.obj;
                    recycleTask(task);
            }
        }
    };

    private TaskManager() {
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        workQueue = new LinkedBlockingQueue<>();
        taskQueue = new LinkedBlockingQueue<>();
        threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue);
    }

    /**
     * 登陆/启动
     *
     * @param phone
     * @param callback
     */
    public void start(String phone, LoginCallback callback) {
        LoginTask task = new LoginTask(phone);
        task.login(callback);
    }

    /**
     * 上传文件
     *
     * @param callback
     */
    public void picUpload(SparseArray<DiagnoseParam> diagnoseParams, final FtdPicUploadCallback callback) {
        schemeId = TextUtils.isEmpty(schemeId) ? Util.getUUID() : schemeId;
        UploadTask task = new UploadTask(user, diagnoseParams, callback);
        task.analyze(this.schemeId);
    }

    /**
     * 获取健康微语
     */
    public void getMicroTip(FtdMicroTipCallback callback) {
        TipTask task = new TipTask(user, callback);
        task.getTip();
    }

    /**
     * 获取问诊问题列表
     */
    public void getQuestionList(FtdQuestionListCallback callback) {
        QuestionTask task = new QuestionTask(this.user, schemeId, callback);
        task.getQuestionList();
    }

    /**
     * 提交问诊答案
     */
    public void submitAnswer(List<QuestionBean> questionList1, List<QuestionBean> questionList2, String traceId1, String traceId2, final FtdSubmitCallback callback) {
        AnswerTask task = new AnswerTask(this.user, questionList1, questionList2, traceId1, traceId2, this.schemeId, callback);
        task.submitAnswer();
    }

    /**
     * 获取检测记录
     * @param seqNO
     * @param callback
     */
    public void getRecord( long seqNO, FtdLastReportCallback callback) {
        RecordTask task = new RecordTask(user, seqNO, callback);
        task.getRecord();
    }

    /**
     * 取消所有任务
     */
    public static void cancelAll() {
        FtdTask[] taskArray = new FtdTask[instance.workQueue.size()];
        instance.workQueue.toArray(taskArray);
        int taskArraylen = taskArray.length;
        synchronized (instance) {
            for (int taskArrayIndex = 0; taskArrayIndex < taskArraylen; taskArrayIndex++) {
                Thread thread = taskArray[taskArrayIndex].getCurrentThread();
                if (null != thread) {
                    thread.interrupt();
                }
            }
        }
    }

    /**
     * 取消单一任务
     *
     * @param task
     */
    public static void cancel(FtdTask task) {
        synchronized (instance) {
            Thread thread = task.getCurrentThread();
            if (null != thread) {
                thread.interrupt();
            }
        }
    }

    /**
     * 根据任务状态做缓存
     *
     * @param task
     * @param state
     */
    public void handleState(FtdTask task, int state) {
        if (!TextUtils.isEmpty(task.getFlag())) {

        }
        switch (state) {
            case TASK_STARTED:

                break;
            case TASK_SUCCEED:

                break;
            case TASK_FAILED:

                break;
            case TASK_CANCELED:
                break;
            default://TASK_COMPLETED
                mainHandler.obtainMessage(state, task).sendToTarget();
        }
    }

    /**
     * 清空任务回收内存
     *
     * @param task
     */
    private void recycleTask(FtdTask task) {
        task.recycle();
        taskQueue.offer(task);
    }
}
