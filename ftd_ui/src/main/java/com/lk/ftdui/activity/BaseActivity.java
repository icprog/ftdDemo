package com.lk.ftdui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lk.ftd_core.task.FtdCore;
import com.lk.ftd_core.task.FtdTask;
import com.lk.ftdui.BasePage;
import com.lk.ftdui.R;
import com.lk.ftdui.activity.config.ErrorDisplay;
import com.lk.ftdui.util.ViewReplaceUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Collection;
import java.util.LinkedList;


abstract class BaseActivity extends AppCompatActivity implements BasePage {

    protected ProgressBar pb;
    protected TextView tbTvStart;
    protected TextView tbTvTitle;
    private TextView tbTvEnd;
    protected SmartRefreshLayout refresh;
    protected ViewReplaceUtil viewReplaceUtil = new ViewReplaceUtil();

    private LinkedList<FtdTask> tasks = new LinkedList<>();

    protected void addTask(FtdTask task) {
        tasks.add(task);
    }

    protected void addTask(Collection<FtdTask> tasks) {
        this.tasks.addAll(tasks);
    }


    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View view = LayoutInflater.from(this).inflate(setContentViewResId(), null);
        setContentView(view);
        Toolbar toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        this.tbTvStart = findViewById(R.id.tb_tv_start);
        this.tbTvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        this.tbTvTitle = findViewById(R.id.tb_tv_title);
        this.tbTvTitle.setText(setTitle());
        pb = findViewById(R.id.pb);
        if (pb != null) {
            pb.setVisibility(setPBDefault() ? View.VISIBLE : View.GONE);
        }
        viewReplaceUtil.setErrorView(setDataEmptyView(), setDataErrorView());
        onCreated(view, savedInstanceState);
    }


    protected View setDataErrorView() {
        return createErrorView(ErrorDisplay.ERROR);
    }

    protected View setDataEmptyView() {
        return createErrorView(ErrorDisplay.EMPTY);
    }

    /**
     * 点击后退按钮时调用
     */
    protected void onBack() {
        finish();
    }


    /**
     * 展示吐司消息
     *
     * @param content
     */
    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
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

    /**
     * 设置进度条是否默认显示，当前默认为显示
     *
     * @return
     */
    protected boolean setPBDefault() {
        return true;
    }

    protected String setTitle() {
        return "";
    }

    protected void setEndTv(TextView tv) {
    }

    @Override
    protected void onStop() {
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            FtdCore.cancel(tasks.removeFirst());
        }
        super.onStop();
    }

    protected View createErrorView(ErrorDisplay error) {
        View errorView = LayoutInflater.from(this).inflate(R.layout.holder_empty, null);
        ImageView ivError = errorView.findViewById(R.id.iv);
        ivError.setImageResource(error.getErrorImg());
        TextView tvError = errorView.findViewById(R.id.tv);
        tvError.setText(error.getContent());
        Button btnRetry = errorView.findViewById(R.id.btn);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewReplaceUtil.restore();
                refresh.autoRefresh();
            }
        });
        return errorView;
    }
}
