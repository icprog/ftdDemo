package com.william.ftdui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdLastReportCallback;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.SixDiseaseBean;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.R;
import com.william.ftdui.widget.view.BarChartView1;
import com.william.ftdui.widget.view.ChartEightPrincipalView;

import java.util.List;

public class ReportActivity extends BaseActivity implements FtdLastReportCallback {

    private ReportBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

//        Intent intent = getIntent();
//        long seqNo = intent.getLongExtra("seqNo", 0);
        long seqNo = 2019070319300000032L;

        FtdClient.getInstance().getLastRecord(seqNo, this);
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

    private void initContent() {
        TextView tv = findViewById(R.id.tv_content);
        tv.setText(bean.getEvaluate());
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
        String content = fdb.getFace() + "\n" + fdb.getTongue() + "\n" + fdb.getMoss();
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

    private void initBarChart(){
        BarChartView1 bcv = findViewById(R.id.bv);
        bcv.setData(bean.getQuotaInfoList());
    }

    @Override
    public void onSuccess(ReportBean bean) {
        this.bean = bean;

        initScore();
        initEightPrincipal();
        initContent();
        initImg();
        initTv100();
        initTv200();
        initBarChart();
//        initBarChart1();
    }

    @Override
    public void onError(FtdException e) {
        showToast(e.getMsg());
    }
}
