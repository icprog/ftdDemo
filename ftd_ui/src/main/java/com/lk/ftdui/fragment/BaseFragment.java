package com.lk.ftdui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lk.ftd_core.task.FtdTask;
import com.lk.ftd_core.task.FtdCore;
import com.lk.ftdui.BasePage;
import com.lk.ftdui.R;

import java.util.LinkedList;

abstract class BaseFragment extends Fragment implements BasePage {

    private ProgressBar pb;

    private LinkedList<FtdTask> tasks = new LinkedList<>();

    protected void addTask(FtdTask task) {
        tasks.add(task);
    }


    @CallSuper
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setContentViewResId(), container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.pb = view.findViewById(R.id.pb);
        onCreated(view, savedInstanceState);
    }

    /**
     * 释放订阅，避免内存泄露
     */

    /**
     * 展示吐司消息
     *
     * @param content
     */
    protected void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展示进度条
     */
    protected void showProgress() {
        if (pb != null && !pb.isEnabled()) {
            pb.setEnabled(true);
            pb.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏进度条（不占位）
     */
    protected void hideProgress() {
        if (pb != null && pb.isEnabled()) {
            pb.setVisibility(View.GONE);
            pb.setEnabled(false);
        }
    }

    /**
     * 隐藏进度条（占位）
     */
    protected void dismissProgress() {
        if (pb != null && pb.isEnabled()) {
            pb.setVisibility(View.INVISIBLE);
            pb.setEnabled(false);
        }
    }

    @Override
    public void onStop() {
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            FtdCore.cancel(tasks.removeFirst());
        }
        super.onStop();
    }

    /**
     * 设置进度条是否默认显示，当前默认为显示
     *
     * @return
     */
    protected boolean setPBDefault() {
        return true;
    }


//    @LayoutRes
//    abstract protected int setContentViewResId();
//
//
//    abstract protected void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
}
