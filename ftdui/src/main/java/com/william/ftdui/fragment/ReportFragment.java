package com.william.ftdui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdGetAnaylzerCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdTendencyCallback;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.R;
import com.william.ftdui.activity.WebActivity;
import com.william.ftdui.widget.aboutRV.adapter.FiveAdapter;
import com.william.ftdui.widget.aboutRV.adapter.ReportAdapter;
import com.william.ftdui.widget.aboutRV.decoration.SpaceDecoration;

import io.reactivex.disposables.Disposable;

public class ReportFragment extends Fragment
        implements FtdLastReportCallback, FtdGetAnaylzerCallback, FtdTendencyCallback, ReportAdapter.OnWuYangSelectListener {

    public static final int REPORT_CARD = 0;
    public static final int REPORT_CONSTITUTION = 1;

    @IntDef({REPORT_CARD, REPORT_CONSTITUTION})
    public @interface ReportType {
    }

    @ReportType
    private int reportType;

    private long seqNo;

    private ReportBean bean;

    private ReportAdapter adapter;

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment newInstance(ReprotFragmentParam param) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putInt("reportType", param.reportType);
        args.putLong("seqNo", param.seqNo);
        fragment.setArguments(args);
        return fragment;
    }

    public static class ReprotFragmentParam {
        @ReportType
        int reportType;
        long seqNo;

        public ReprotFragmentParam(int reportType, long seqNo) {
            this.reportType = reportType;
            this.seqNo = seqNo;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            reportType = getArguments().getInt("reportType");
            seqNo = getArguments().getLong("seqNo");
            adapter = new ReportAdapter(reportType, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv);
        rv.addItemDecoration(new SpaceDecoration());
        rv.setAdapter(adapter);
        Disposable disposable = FtdClient.getInstance().getRecordBySeqNo(seqNo, this);
//        addDisposable(disposable);
    }

    @Override
    public void onWuYangSelect(FiveAdapter.Five fiveBean) {
        if (bean == null || bean.getUr() == null || bean.getUr().get(0) == null) {
            return;
        }
        String diseaseId = bean.getUr().get(0).getDiseaseId();
        if (!TextUtils.isEmpty(diseaseId)) {
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra("title", fiveBean.getTitle());
            intent.putExtra("url", fiveBean.getUrl(diseaseId, FtdClient.getInstance().getAppKey()));
            startActivity(intent);
        }
    }

    @Override
    public void onSuccess(ReportBean bean) {
        this.bean = bean;
//        addDisposable(
        FtdClient.getInstance().getAnalyzer(bean.getUr(), this);
//    );
//        addDisposable(
        FtdClient.getInstance().getTendency(this);
//    );
        adapter.setData(this.bean);
    }

    @Override
    public void onError(FtdException e) {
        int i = 0;
        int ii = 0;
    }

    @Override
    public void onSuccess(AnalyzeResultBean bean) {
//        hideProgress();
        this.bean.setAnalyzeResultBean(bean);
        this.adapter.notifyItemChanged(1);
        this.adapter.notifyItemChanged(5);
    }

    @Override
    public void onSuccess(TendencyResult result) {
//        hideProgress();
        this.bean.setTendencyResult(result);
        this.adapter.notifyItemChanged(4);
    }
}
