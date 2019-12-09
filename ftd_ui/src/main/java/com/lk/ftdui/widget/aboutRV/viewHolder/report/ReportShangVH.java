package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.lk.ftd_core.entity.ReportBean;
import com.william.zhibiaoview.DashBoardView;

public class ReportShangVH extends ReportBaseVH {

    private DashBoardView dash;

    public ReportShangVH(@NonNull View itemView) {
        super(itemView);
        dash = (DashBoardView) itemView;
    }

    @Override
    public void bind(int reportType, ReportBean bean) {
        dash.setValue((float)bean.getEntropyScore());
    }
}
