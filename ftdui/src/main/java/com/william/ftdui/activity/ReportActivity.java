package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
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
import com.william.ftdui.R;
import com.william.ftdui.widget.adapter.AnalyzeAdapter;
import com.william.ftdui.widget.adapter.viewHolder.FiveAdapter;
import com.william.ftdui.widget.view.BarChartView1;
import com.william.ftdui.widget.view.ChartEightPrincipalView;
import com.william.zhibiaoview.ZheXianView;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ReportActivity extends BaseActivity
        implements FtdLastReportCallback, FtdGetAnaylzerCallback, FtdTendencyCallback {

    private ReportBean bean;

    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();
        long seqNo = intent.getLongExtra("seqNo", 2019070319300000032L);

        Disposable disposable1 = FtdClient.getInstance().getRecordBySeqNo(seqNo, this);
        addDisposable(disposable1);
        Disposable disposable2 = FtdClient.getInstance().getTendency(this);
        addDisposable(disposable2);
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_report;
    }

    /**
     * 初始化分数和描述
     */
    private void initScore() {
        if (bean.getEight() == null) {
            return;
        }
        double score = bean.getScore();
        TextView tvScore = findViewById(R.id.tv_score);
        SpannableString ss = new SpannableString(String.valueOf(score) + "分");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
        ss.setSpan(sizeSpan, ss.length() - 1, ss.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvScore.setText(ss);
    }

    /**
     * 初始化八纲图
     */
    private void initEightPrincipal() {
        ChartEightPrincipalView view = findViewById(R.id.epv);
        if (bean.getEight() != null && bean.getEight().getSixDiseaseList() != null) {
            String sixDiseaseStr = bean.getEight().getSixDiseaseList();
            Gson gson = new Gson();
            SixDiseaseBean[] diseaseBeans = gson.fromJson(sixDiseaseStr, SixDiseaseBean[].class);
            view.setData(diseaseBeans);
        }
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
        if (bean == null || bean.getFaceDiagnose() == null) {
            return;
        }
        ReportBean.FaceDiagnoseBean fdb = bean.getFaceDiagnose();
        String content = "●" + fdb.getFace() + "\n●" + fdb.getTongue() + "\n●" + fdb.getMoss();
        tv.setText(content);
    }

    /**
     * 初始化指标分解
     */
    private void initTv200() {
        if (bean == null || bean.getQm() == null) {
            return;
        }
        TextView tv = findViewById(R.id.tv_200);
        List<ReportBean.QmBean> qm = bean.getQm();
        StringBuffer content = new StringBuffer();
        for (ReportBean.QmBean qmBean : qm) {
            String cause = "";
            List<ReportBean.QmBean.CauseListBean> list = qmBean.getCauseList();
            if (list != null && list.size() > 0) {
                cause = list.get(0).getCause();
            }
            content.append(qmBean.getName()).append("-").append(qmBean.getText()).append(":").append(cause).append("。\n");
        }
        tv.setText(content.toString());
    }

    /**
     * 初始化柱形图
     */
    private void initBarChart() {
        if (bean == null || bean.getQuotaInfoList() == null) {
            return;
        }
        BarChartView1 bcv = findViewById(R.id.bv);
        bcv.setData(bean.getQuotaInfoList());
    }

    /**
     * 初始化指数分解
     */
    private void initRv2() {
        if (bean == null || bean.getQuotaInfoList() == null) {
            return;
        }
        RecyclerView rv = findViewById(R.id.rv2);
        rv.setAdapter(new AnalyzeAdapter(bean.getQuotaInfoList()));
    }

    /**
     * 初始化五养
     */
    private void initFive() {
        if (bean == null || bean.getUr() == null) {
            return;
        }
        final String diseaseId = bean.getUr().get(0).getDiseaseId();
        RecyclerView fiveList = findViewById(R.id.rv_five);
        FiveAdapter adapter = new FiveAdapter(new FiveAdapter.OnWuYangSelectListener() {
            @Override
            public void onWuYangSelect(FiveAdapter.Five fiveBean) {
                Intent intent = new Intent(ReportActivity.this, WebActivity.class);
                intent.putExtra("title", fiveBean.getTitle());
                intent.putExtra("url", fiveBean.getUrl(diseaseId, FtdClient.getInstance().getAppKey()));
                startActivity(intent);
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
        ZheXianView zheXianView = findViewById(R.id.zhexian);

        List<TendencyResult.ResultBean> list = tendencyResult.getResult();
        LinkedList<ZheXianView.Data> dataSeries = new LinkedList<>();
        int size = list.size();
        for (int i = size - 1; i >= 0; i--) {
            dataSeries.add(new ZheXianView.Data(dateFormate(list.get(i).getDate()), (float) list.get(i).getScore()));
        }
        zheXianView.initData(dataSeries);
    }

    private String dateFormate(String date) {
        String[] strs = date.split("-");
        StringBuilder sb = new StringBuilder(strs[1]);
        sb.append(".");
        sb.append(strs[2]);
        return sb.toString();
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
     *
     * @param bean
     */
    private void initHealthy(AnalyzeResultBean bean) {
        TextView tv = findViewById(R.id.tv_helthy_analyzer);
        StringBuffer sb = new StringBuffer();
        for (AnalyzeResultBean.Data data : bean.getDataList()) {
            sb.append("●").append(data.getName()).append("\n\t").append(data.getIntro()).append("\n");
        }
        tv.setText(sb.toString());
    }

    @Override
    public void onSuccess(AnalyzeResultBean bean) {
        hideProgress();
        initContent(bean);
        initHealthy(bean);
    }

    @Override
    public void onSuccess(TendencyResult result) {
        hideProgress();
        initTendency(result);
    }

    @Override
    public void onError(FtdException e) {
        hideProgress();
        showToast(e.getMsg());
    }

}