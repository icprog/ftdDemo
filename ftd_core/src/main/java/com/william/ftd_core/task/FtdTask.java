package com.william.ftd_core.task;

import com.william.ftd_core.TaskManager;

import java.lang.ref.WeakReference;

public abstract class FtdTask<T> {

    private String flag;

    private static TaskManager taskManager;
    protected Thread mCurrentThread;
    protected Runnable runnable;
    protected WeakReference<T> weakCallback;

//    private ArrayList<WeakReference> uiList = new ArrayList<>(10);


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public FtdTask() {
        taskManager = TaskManager.instance;
    }

    public Thread getCurrentThread() {
        synchronized (taskManager) {
            return mCurrentThread;
        }
    }

    public void setCurrentThread(Thread mCurrentThread) {
        synchronized (taskManager) {
            this.mCurrentThread = mCurrentThread;
        }
    }

    abstract public Runnable getRunnable();

    /**
     * 回收方法，用于释放与页面元素的弱引用
     */
//    abstract public void recycle();

//    @Override
    public void recycle() {
        if (null != weakCallback) {
            weakCallback.clear();
            weakCallback = null;
        }
    }

    /**
     * 一次性回收所有UI引用（慎用）
     */
    public void recycleAll() {
//        for (WeakReference weakReference : uiList) {
//            if (null != weakReference) {
//                weakReference.clear();
//                weakReference = null;
//            }
//        }
//        uiList.clear();
    }
}
