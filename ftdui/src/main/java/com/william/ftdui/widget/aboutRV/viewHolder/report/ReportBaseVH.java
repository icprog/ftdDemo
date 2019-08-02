package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.fragment.ReportFragment;

public abstract class ReportBaseVH extends RecyclerView.ViewHolder {

    public ReportBaseVH(@NonNull View itemView) {
        super(itemView);
    }

    abstract public void bind(@ReportFragment.ReportType int reportType,ReportBean bean);
}
