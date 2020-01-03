package com.lk.ftdui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.TaskManager;
import com.lk.ftd_core.callback.FtdDoctorListCallback;
import com.lk.ftd_core.entity.DoctorListBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.R;
import com.lk.ftdui.activity.param.DoctorInfo;
import com.lk.ftdui.widget.aboutRV.adapter.DoctorListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class DoctorListActivity extends BaseActivity {

    private SmartRefreshLayout refresh;
    private int pageIndex = 1;//页数从1开始
    private View ivError;

    public static void start(Context context) {
        Intent intent = new Intent(context, DoctorListActivity.class);
        context.startActivity(intent);
    }

    private DoctorListAdapter adapter = new DoctorListAdapter(new DoctorListAdapter.OnSelectedListener() {
        @Override
        public void onDoctorSelected(DoctorListBean.DoctorBean item) {
            DoctorDetailActivity.start(DoctorListActivity.this, item.getMemberDTO().getMemberCode());
        }

        @Override
        public void onDoctorChoosed(DoctorListBean.DoctorBean item) {
            DoctorInfo info = new DoctorInfo(item.getMemberDTO().getMemberId(), item.getMemberDTO().getMemberCode());
            FtdActivity.startWithThreePhoto(DoctorListActivity.this, info);
        }
    });

    private int pageCount;
    private FtdDoctorListCallback callback = new FtdDoctorListCallback() {

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

        @Override
        public void onSuccess(final DoctorListBean bean) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pageIndex = bean.getCurrentPage();
                    pageCount = bean.getTotalPage();
                    hideProgress();
                    refresh.finishLoadMore();
                    refresh.finishRefresh();
                    adapter.submitList(bean.getList());
                }
            });
        }
    };

    @Override
    public int setContentViewResId() {
        return R.layout.activity_doctor_list;
    }


    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setAdapter(this.adapter);
        refresh = findViewById(R.id.refreshLayout);
        refresh.autoRefresh();
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (pageIndex < pageCount) {
                    TaskManager.instance.getDoctorList(pageIndex + 1, callback);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                TaskManager.instance.getDoctorList(pageIndex, callback);
            }
        });
        TextView title = findViewById(R.id.tb_tv_title);
        title.setText("选择来康师");
        TextView past = findViewById(R.id.tb_tv_end);
        past.setText("跳过");
        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FtdActivity.startWithThreePhoto(DoctorListActivity.this);
            }
        });
        ivError = findViewById(R.id.iv_error);
    }
}
