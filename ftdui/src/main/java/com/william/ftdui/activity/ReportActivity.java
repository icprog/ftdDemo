package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdGetAnaylzerCallback;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.callback.FtdTendencyCallback;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.SixDiseaseBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.BuildConfig;
import com.william.ftdui.R;
import com.william.ftdui.widget.adapter.AnalyzeAdapter;
import com.william.ftdui.widget.adapter.decoration.GridDividerItemDecoration;
import com.william.ftdui.widget.adapter.viewHolder.FiveAdapter;
import com.william.ftdui.widget.view.BarChartView1;
import com.william.ftdui.widget.view.ChartEightPrincipalView;
import com.william.ftdui.widget.view.ChartLineView;

import java.util.List;

public class ReportActivity extends BaseActivity
        implements FtdLastReportCallback, FtdGetAnaylzerCallback, FtdTendencyCallback {

    private ReportBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

//        Intent intent = getIntent();
//        long seqNo = intent.getLongExtra("seqNo", 0);
        long seqNo = 2019070319300000032L;

        FtdClient.getInstance().getLastRecord(seqNo, this);

        FtdClient.getInstance().getTendency(this);
    }

    /**
     * 初始化分数和描述
     */
    private void initScore() {
        int score = (int) bean.getEight().getTotalScore();
        TextView tvScore = findViewById(R.id.tv_score);
        SpannableString ss = new SpannableString(String.valueOf(score) + "分");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
        ss.setSpan(sizeSpan, ss.length() - 1, ss.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvScore.setText(ss);

        TextView tvScoreDes = findViewById(R.id.tv_score_des);
//        tvScoreDes.setText(bean.getCardInfo().getEvaluate());


    }

    /**
     * 初始化八纲图
     */
    private void initEightPrincipal() {
        ChartEightPrincipalView view = findViewById(R.id.epv);

        String sixDiseaseStr = bean.getEight().getSixDiseaseList();
        Gson gson = new Gson();
        SixDiseaseBean[] diseaseBeans = gson.fromJson(sixDiseaseStr, SixDiseaseBean[].class);
        view.setData(diseaseBeans);
    }

    /**
     * 初始化专家点评
     *
     * @param bean
     */
    private void initContent(AnalyzeResultBean bean) {
        TextView tv = findViewById(R.id.tv_content);
        tv.setText(bean.getOpinion());
    }

    private void initImg() {
        ImageView ivFace = findViewById(R.id.iv_face);
        ImageView ivTongueTop = findViewById(R.id.iv_tongue_top);
        Glide.with(ivFace).load(bean.getFaceImg()).into(ivFace);
        Glide.with(ivTongueTop).load(bean.getTongueImg()).into(ivTongueTop);
    }

    /**
     * 初始化指标结果
     */
    private void initTv100() {
        TextView tv = findViewById(R.id.tv_100);
        ReportBean.FaceDiagnoseBean fdb = bean.getFaceDiagnose();
        String content = "●" + fdb.getFace() + "\n●" + fdb.getTongue() + "\n●" + fdb.getMoss();
        tv.setText(content);
    }

    /**
     * 初始化指标分解
     */
    private void initTv200() {
        TextView tv = findViewById(R.id.tv_200);
        List<ReportBean.QmBean> qm = bean.getQm();
        StringBuffer content = new StringBuffer();
        for (ReportBean.QmBean qmBean : qm) {
            content.append(qmBean.getName()).append("-").append(qmBean.getText()).append(":").append(qmBean.getCauseList().get(0).getCause()).append("。\n");
        }
        tv.setText(content.toString());
    }

    /**
     * 初始化柱形图
     */
    private void initBarChart() {
        BarChartView1 bcv = findViewById(R.id.bv);
        bcv.setData(bean.getQuotaInfoList());
    }

    /**
     * 初始化指数分解
     */
    private void initRv2() {
        RecyclerView rv = findViewById(R.id.rv2);
        rv.setAdapter(new AnalyzeAdapter(bean.getQuotaInfoList()));
    }

    /**
     * 初始化五养
     */
    private void initFive() {
        RecyclerView fiveList = findViewById(R.id.rv_five);
        FiveAdapter adapter = new FiveAdapter("", new FiveAdapter.OnWuYangSelectListener() {
            @Override
            public void onWuYangSelect(String url) {
                //todo 跳转五养
                showToast(url);
            }
        });
        fiveList.setAdapter(adapter);
    }

    /**
     * 初始化趋势图
     *
     * @param tendencyResult
     */
    private void initTendency(TendencyResult tendencyResult) {
        ChartLineView clv = findViewById(R.id.chart_line);
        clv.setData(tendencyResult);
    }

    @Override
    public void onSuccess(ReportBean bean) {
        this.bean = bean;

        FtdClient.getInstance().getAnalyzer(bean.getUr(), this);

        initScore();
        initEightPrincipal();

        initImg();
        initTv100();
        initTv200();
        initBarChart();
        initRv2();
        initFive();


    }

    /**
     * 健康分析
     * @param bean
     */
    private void initHealthy(AnalyzeResultBean bean) {
        TextView tv = findViewById(R.id.tv_helthy_analyzer);
        StringBuffer sb = new StringBuffer();
        for (AnalyzeResultBean.Data data: bean.getDataList()) {
            sb.append("●").append(data.getName()).append("\n\t").append(data.getIntro()).append("\n");
        }
        tv.setText(sb.toString());
    }

    @Override
    public void onSuccess(AnalyzeResultBean bean) {
        initContent(bean);
        initHealthy(bean);
    }

    @Override
    public void onSuccess(TendencyResult result) {
        initTendency(result);
    }

    @Override
    public void onError(FtdException e) {
        showToast(e.getMsg());
    }

}
