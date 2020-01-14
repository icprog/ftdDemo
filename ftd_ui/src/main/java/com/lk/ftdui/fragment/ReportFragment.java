package com.lk.ftdui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lk.ftd_core.callback.FtdLastReportCallback;
import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftd_core.task.FtdCore;
import com.lk.ftdui.BuildConfig;
import com.lk.ftdui.R;
import com.lk.ftdui.activity.WebActivity;
import com.lk.ftdui.activity.param.DoctorInfo;
import com.lk.ftdui.widget.aboutRV.adapter.FiveAdapter;
import com.lk.ftdui.widget.aboutRV.adapter.ReportAdapter;
import com.lk.ftdui.widget.aboutRV.decoration.SpaceDecoration;
import com.lk.ftdui.widget.dialog.ShangDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.william.zhibiaoview.DashBoardView;

public class ReportFragment extends BaseFragment implements ReportAdapter.OnWuYangSelectListener, DashBoardView.OnQuestionMarkClickListener,FtdLastReportCallback {

    private ReportType reportType;



    private Listener mListener;

    private ShangDialog shangDialog = new ShangDialog();

    private String seqNo;

    private DoctorInfo doctorInfo;

    private ReportBean bean;

    private ReportAdapter adapter;

    private SmartRefreshLayout refresh;

    @Override
    public void onError(FtdException e) {
        hideProgress();
        refresh.finishRefresh();
    }

    @Override
    public void onSuccess(final ReportBean bean) {
        ReportFragment.this.bean = bean;
        hideProgress();
        refresh.finishRefresh();
        adapter.setData(bean);
        mListener.onGetScore(bean.getScore());
    }

//    private FtdLastReportCallback reportCallback = new FtdLastReportCallback() {
//
//        @Override
//        public void onError(FtdException e) {
//            hideProgress();
//            refresh.finishRefresh();
//        }
//
//        @Override
//        public void onSuccess(final ReportBean bean) {
//            ReportFragment.this.bean = bean;
//            hideProgress();
//            refresh.finishRefresh();
//            adapter.setData(bean);
//            mListener.onGetScore(bean.getScore());
//        }
//    };

    public ReportFragment() {
        // Required empty public constructor
    }

    public static ReportFragment newInstance(ReprotFragmentParam param) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putInt("reportType", param.reportType);
        args.putString("seqNo", param.seqNo);
        args.putParcelable("doctorInfo", param.doctorInfo);
        fragment.setArguments(args);
        return fragment;
    }

    public static class ReprotFragmentParam {

        int reportType;
        String seqNo;
        DoctorInfo doctorInfo;

        public ReprotFragmentParam(int reportType, String seqNo, DoctorInfo doctorInfo) {
            this.reportType = reportType;
            this.seqNo = seqNo;
            this.doctorInfo = doctorInfo;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            reportType = ReportType.values()[getArguments().getInt("reportType")];
            seqNo = getArguments().getString("seqNo");
            doctorInfo = getArguments().getParcelable("doctorInfo");
            adapter = new ReportAdapter(reportType, this, this);
        }
    }

    @Override
    public int setContentViewResId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        refresh = view.findViewById(R.id.refreshLayout);
        refresh.autoRefresh();
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String doctorID = null;
                String doctorMemberCode = null;
                if (doctorInfo != null) {
                    doctorID = doctorInfo.getDoctorID();
                    doctorMemberCode = doctorInfo.getDoctorMemberCode();
                }
                addTask(FtdCore.instance.getRecord(seqNo, doctorID, doctorMemberCode, reportType, true, ReportFragment.this));
            }
        });
        RecyclerView rv = view.findViewById(R.id.rv);
        rv.addItemDecoration(new SpaceDecoration());
        rv.setAdapter(adapter);
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
            intent.putExtra("url", BuildConfig.BRAIN_WEB);
            startActivity(intent);
        }
    }

    public void setScore(double score) {
        if (adapter != null) {
            adapter.setScore(score);
        }
    }


    @Override
    public void OnQuestionMarkClicked() {
        shangDialog.show(getChildFragmentManager(), "dialog");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            this.mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (this.mListener != null) {
            this.mListener = null;
        }
    }

    public interface Listener {
        void onGetScore(double score);
    }
}
