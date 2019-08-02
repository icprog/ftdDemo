package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.SixDiseaseBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.view.ChartEightPrincipalView;

/**
 * 八纲图板块
 */
public class ReportScoreVH extends ReportBaseVH {

    private ChartEightPrincipalView view;
    TextView tvDisease;
    TextView tvScore;
    TextView tvDate;
    TextView tvScoreMain;
    Context context;

    public ReportScoreVH(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        view = itemView.findViewById(R.id.epv);
        tvDisease = itemView.findViewById(R.id.tv_disease);
        tvScore = itemView.findViewById(R.id.tv_score);
        tvDate = itemView.findViewById(R.id.tv_date);
        tvScoreMain = itemView.findViewById(R.id.tv_score_main);
    }

    /**
     * 初始化八纲图
     */
    public void bind(@ReportFragment.ReportType int reportType, ReportBean bean) {
        tvScoreMain.setText(String.valueOf(bean.getScore()));
        String text = "健康得分" + bean.getScore() + "分";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs = new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary));
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(26, true);
        ss.setSpan(fcs, 4, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(ass, 4, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvScore.setText(ss);

        if (bean.getEight() != null && bean.getEight().getSixDiseaseList() != null) {
            String sixDiseaseStr = bean.getEight().getSixDiseaseList();
            Gson gson = new Gson();
            SixDiseaseBean[] diseaseBeans = gson.fromJson(sixDiseaseStr, SixDiseaseBean[].class);
            view.setData(diseaseBeans);
        }

        if (bean.getUr() != null && !bean.getUr().isEmpty()) {
            tvDisease.setText("您疑似：" + bean.getUr().get(0).getDiseaseName());
        }

        tvDate.setText(bean.getCreateTime() + "检测");
    }
}
