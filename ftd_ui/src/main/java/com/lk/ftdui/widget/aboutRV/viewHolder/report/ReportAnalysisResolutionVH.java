package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;
import com.lk.ftdui.widget.view.BarChartView;

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
