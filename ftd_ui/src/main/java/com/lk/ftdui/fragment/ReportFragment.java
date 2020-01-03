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

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftdui.activity.WebActivity;
import com.lk.ftdui.activity.param.DoctorInfo;
import com.lk.ftdui.widget.aboutRV.adapter.FiveAdapter;
import com.lk.ftdui.widget.aboutRV.adapter.ReportAdapter;
import com.lk.ftdui.widget.aboutRV.decoration.SpaceDecoration;
import com.lk.ftd_core.TaskManager;
import com.lk.ftd_core.callback.FtdLastReportCallback;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.R;

public class ReportFragment extends BaseFragment implements ReportAdapter.OnWuYangSelectListener {

    private ReportType reportType;

    /**
     * 八纲图key
     */
    public static final int EIGHT = 0;//八纲图
    /**
     * 总得分key
     */
    public static final int SCORE = 1;//八纲图
    /**
     * 专家点评key
     */
    public static final int PROFESSOR = 2;//专家点评
    /**
     * 指数分析key
     */
    public static final int RESOLUTION = 3;
    /**
     * 指标结果key
     */
    public static final int RESULT = 4;
    /**
     * 趋势分析
     */
    public static final int TENDENCY = 5;
    /**
     * 健康分析key
     */
    public static final int HEATH = 6;
    /**
     * 指标分析
     */
    public static final int INDEX = 7;
    /**
     * 五养key
     */
    public static final int FIVE = 8;

    private Listener mListener;

    @IntDef({EIGHT, SCORE, PROFESSOR, RESOLUTION, RESULT, TENDENCY, HEATH, INDEX, FIVE})
    public @interface Plate {
    }

    private String seqNo;

    private DoctorInfo doctorInfo;

    private ReportBean bean;

    private ReportAdapter adapter;

    private Handler mainHandler = new Handler(Looper.getMainLooper());

    private FtdLastReportCallback reportCallback = new FtdLastReportCallback() {

        @Override
        public void onError(FtdException e) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                }
            });
        }

        @Override
        public void onSuccess(final ReportBean bean) {
            ReportFragment.this.bean = bean;
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    hideProgress();
                    adapter.setData(bean);
                    mListener.onGetScore(bean.getScore());
                }
            });

        }
    };

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
            adapter = new ReportAdapter(reportType, this);
        }
    }

    @Override
    public int setContentViewResId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = view.findViewById(R.id.rv);
        rv.addItemDecoration(new SpaceDecoration());
        rv.setAdapter(adapter);
        String doctorID = null;
        String doctorMemberCode = null;
        if (doctorInfo != null) {
            doctorID = doctorInfo.getDoctorID();
            doctorMemberCode = doctorInfo.getDoctorMemberCode();
        }
        TaskManager.instance.getRecord(seqNo, doctorID, doctorMemberCode, reportType, reportCallback);
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
//            intent.putExtra("url", fiveBean.getUrl(diseaseId, FtdClient.getInstance().getAppKey()));
            startActivity(intent);
        }
    }

    public void setScore(double score) {
        if (adapter != null) {
            adapter.setScore(score);
        }
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
