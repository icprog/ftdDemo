package com.lk.ftdui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.TaskManager;
import com.lk.ftd_core.callback.FtdRecordListCallback;
import com.lk.ftd_core.entity.RecordListBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.adapter.RecordAdapter;
import com.lk.ftdui.widget.aboutRV.decoration.SpaceDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class RecordListActivity extends BaseActivity {

    private SmartRefreshLayout refresh;
    private int pageIndex = 1;//页数从1开始
    private int pageCount;
    private View ivError;

    public static void start(Context context) {
        context.startActivity(new Intent(context, RecordListActivity.class));
    }

    private RecordAdapter adapter = new RecordAdapter(new RecordAdapter.OnSelectedListener() {

        @Override
        public void onItemSelected(RecordListBean.RecordBean item) {
            ReportActivity.start(RecordListActivity.this, item.getSeqNo(), item.getConstitutionSeqNo());
        }
    });

    private FtdRecordListCallback callback = new FtdRecordListCallback() {
        @Override
        public void onSuccess(final RecordListBean bean) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pageIndex = bean.getPageNum();
                    pageCount = bean.getPages();
                    hideProgress();
                    refresh.finishLoadMore();
                    refresh.finishRefresh();
                    adapter.submitList(bean.getList());
                }
            });
        }

        @Override
        public void onError(final FtdException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                    refresh.finishLoadMore();
                    refresh.finishRefresh();
                    showToast(e.getMsg());
                }
            });
        }
    };

    @Override
    public int setContentViewResId() {
        return R.layout.activity_record;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(adapter);
        refresh = findViewById(R.id.refreshLayout);
        refresh.autoRefresh();
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                TaskManager.instance.listRecord(pageIndex, callback);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                TaskManager.instance.listRecord(pageIndex, callback);
            }
        });
        TextView title = findViewById(R.id.tb_tv_title);
        title.setText("选择来康师");
        refresh.autoRefresh();
    }
}
