package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdGetAnaylzerCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdTendencyCallback;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.adapter.FiveAdapter;
import com.william.ftdui.widget.aboutRV.adapter.ReportAdapter;
import com.william.ftdui.widget.aboutRV.decoration.SpaceDecoration;

import io.reactivex.disposables.Disposable;

public class ReportActivity extends BaseActivity
        implements FtdLastReportCallback, FtdGetAnaylzerCallback, FtdTendencyCallback, ReportAdapter.OnWuYangSelectListener {

    private ReportBean bean;

    private ReportAdapter adapter = new ReportAdapter(this);


    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {

        RecyclerView rv = findViewById(R.id.rv);
        rv.addItemDecoration(new SpaceDecoration());
        rv.setAdapter(adapter);

        Intent intent = getIntent();
        long seqNo = intent.getLongExtra("seqNo", 2019070319300000032L);//todo

        Disposable disposable = FtdClient.getInstance().getRecordBySeqNo(seqNo, this);
        addDisposable(disposable);

    }

    @Override
    protected String setTitle() {
        return "评估报告";
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_report;
    }


    @Override
    public void onSuccess(ReportBean bean) {
        this.bean = bean;
        addDisposable(FtdClient.getInstance().getAnalyzer(bean.getUr(), this));
        addDisposable(FtdClient.getInstance().getTendency(this));
        adapter.setData(this.bean);
    }

    @Override
    public void onSuccess(AnalyzeResultBean bean) {
        hideProgress();
        this.bean.setAnalyzeResultBean(bean);
        this.adapter.notifyItemChanged(1);
        this.adapter.notifyItemChanged(5);

    }

    @Override
    public void onSuccess(TendencyResult result) {
        hideProgress();
        this.bean.setTendencyResult(result);
        this.adapter.notifyItemChanged(4);
    }

    @Override
    public void onError(FtdException e) {
        hideProgress();
        showToast(e.getMsg());
    }

    @Override
    public void onWuYangSelect(FiveAdapter.Five fiveBean) {
        if (bean == null || bean.getUr() == null || bean.getUr().get(0) == null) {
            return;
        }
        String diseaseId = bean.getUr().get(0).getDiseaseId();
        if (!TextUtils.isEmpty(diseaseId)) {
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("title", fiveBean.getTitle());
            intent.putExtra("url", fiveBean.getUrl(diseaseId, FtdClient.getInstance().getAppKey()));
            startActivity(intent);
        }
    }
}