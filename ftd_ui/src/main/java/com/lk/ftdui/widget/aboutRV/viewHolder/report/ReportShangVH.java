package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.ReportBean;
import com.william.zhibiaoview.DashBoardView;

public class ReportShangVH extends ReportBaseVH {

    private DashBoardView dash;
    private DashBoardView.OnQuestionMarkClickListener listener;

    public void setListener(DashBoardView.OnQuestionMarkClickListener listener) {
        this.listener = listener;
        dash.setOnQuestionMarkClickListener(listener);
    }

    public ReportShangVH(@NonNull View itemView) {
        super(itemView);
        dash = (DashBoardView) itemView;
    }

    @Override
    public void bind(ReportType reportType, ReportBean bean) {
        dash.setValue(bean.getEntropyScore());
    }
}
