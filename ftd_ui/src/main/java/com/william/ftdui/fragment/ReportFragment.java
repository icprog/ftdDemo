package com.william.ftdui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.TaskManager;
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

public class ReportFragment extends BaseFragment
        implements FtdLastReportCallback, FtdGetAnaylzerCallback, FtdTendencyCallback, ReportAdapter.OnWuYangSelectListener {

    public static final int REPORT_CARD = 0;
    public static final int REPORT_CONSTITUTION = 1;

    @IntDef({REPORT_CARD, REPORT_CONSTITUTION})
    public @interface ReportType {
    }

    @ReportType
    private int reportType;

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
    public int setContentViewResId() {
        return R.layout.fragment_card;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = view.findViewById(R.id.rv);
        rv.addItemDecoration(new SpaceDecoration());
        rv.setAdapter(adapter);
        TaskManager.instance.getRecord(seqNo,this);
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

    public void setScore(double score) {
        if (adapter != null) {
            adapter.setScore(score);
        }
    }

    @Override
    public void onSuccess(ReportBean bean) {
        this.bean = bean;
        addDisposable(
                FtdClient.getInstance().getAnalyzer(bean.getUr(), this)
        );
        addDisposable(
                FtdClient.getInstance().getTendency(this)
        );

//        if (reportType == REPORT_CARD){
//            bean.setScore(1.1d);
//        }
        adapter.setData(this.bean);
        mListener.onGetScore(this.bean.getScore());
    }

    @Override
    public void onError(FtdException e) {
        showToast(e.getMsg());
    }

    @Override
    public void onSuccess(AnalyzeResultBean bean) {
        hideProgress();
        this.bean.setAnalyzeResultBean(bean);
        this.adapter.notifyItem(PROFESSOR);
        this.adapter.notifyItem(HEATH);
    }

    @Override
    public void onSuccess(TendencyResult result) {
        hideProgress();
        this.bean.setTendencyResult(result);
        this.adapter.notifyItem(TENDENCY);
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
