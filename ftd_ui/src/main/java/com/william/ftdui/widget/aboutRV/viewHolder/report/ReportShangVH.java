package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.zhibiaoview.DashBoardView;

public class ReportShangVH extends ReportBaseVH {

    private DashBoardView dash;

    public ReportShangVH(@NonNull View itemView) {
        super(itemView);
        dash = itemView.findViewById(R.id.dash);
    }

    @Override
    public void bind(int reportType, ReportBean bean) {
        dash.setValue(bean.);
    }
}
