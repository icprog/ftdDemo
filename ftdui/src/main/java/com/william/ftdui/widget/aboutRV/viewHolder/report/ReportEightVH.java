package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.gson.Gson;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.SixDiseaseBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.view.ChartEightPrincipalView;

/**
 * 八纲图板块
 */
public class ReportEightVH extends ReportBaseVH {

    private ChartEightPrincipalView view;

    public ReportEightVH(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.epv);
    }

    /**
     * 初始化八纲图
     */
    public void bind(ReportBean bean) {
        if (bean.getEight() != null && bean.getEight().getSixDiseaseList() != null) {
            String sixDiseaseStr = bean.getEight().getSixDiseaseList();
            Gson gson = new Gson();
            SixDiseaseBean[] diseaseBeans = gson.fromJson(sixDiseaseStr, SixDiseaseBean[].class);
            view.setData(diseaseBeans);
        }
    }
}
