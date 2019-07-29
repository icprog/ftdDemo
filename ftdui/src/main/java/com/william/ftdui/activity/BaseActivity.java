package com.william.ftdui.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.william.ftdui.R;

import java.util.LinkedList;

import io.reactivex.disposables.Disposable;

abstract class BaseActivity extends AppCompatActivity {

    private ProgressBar pb;
    private TextView tbTvStart;
    private TextView tbTvTitle;
    private TextView tbTvEnd;

    private LinkedList<Disposable> dosposableList = new LinkedList<>();

    protected void addDisposable(Disposable disposable) {
        dosposableList.add(disposable);
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewResId());
        Toolbar toolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        this.tbTvStart = findViewById(R.id.tb_tv_start);
        this.tbTvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavClicked();
            }
        });
        this.tbTvTitle = findViewById(R.id.tb_tv_title);
        this.tbTvTitle.setText(setTitle());
        this.tbTvEnd = findViewById(R.id.tb_tv_end);
        setEndTv(this.tbTvEnd);
        pb = findViewById(R.id.pb);
        pb.setVisibility(setPBDefault() ? View.VISIBLE : View.GONE);
        setTitle("");
        onCreated(savedInstanceState);
    }

    protected void onNavClicked(){
        finish();
    }

    /**
     * 释放订阅，避免内存泄露
     */
    private void dispose() {
        for (Disposable disposable : dosposableList) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
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


//    protected final TextView getTbTvEnd() {
//        return tbTvEnd;
//    }
//
//    protected final TextView getTbTvTitle() {
//        return tbTvTitle;
//    }








    @LayoutRes
    abstract protected int setContentViewResId();

    protected String setTitle(){
        return "";
    }

    protected void setEndTv(TextView tv){ }

    abstract protected void onCreated(@Nullable Bundle savedInstanceState);
}
