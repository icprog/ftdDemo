package com.william.ftd_core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.william.ftd_core.runnable.LoginRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


    private ThreadPoolExecutor threadPool;

    private Handler mainHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private TaskManager() {
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        workQueue = new LinkedBlockingQueue<>();
        threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue);
    }

    public static void start(String phone, LoginRunnable.LoginCallback callback) {
        FtdTask task = new LoginTask(phone,callback);
        instance.threadPool.execute(task.getRunnable());
    }
}
