package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.view.BarChartView;

public class ReportAnalysisResolutionVH extends ReportBaseVH {

    private BarChartView bcv;

    public ReportAnalysisResolutionVH(@NonNull View itemView) {
        super(itemView);
        bcv = itemView.findViewById(R.id.bv);
    }

    @Override
    public void bind(@ReportFragment.ReportType int reportType, ReportBean bean) {
        if (bean.getQuotaInfoList() != null) {
            bcv.setData(bean.getQuotaInfoList());
        }
    }
}
