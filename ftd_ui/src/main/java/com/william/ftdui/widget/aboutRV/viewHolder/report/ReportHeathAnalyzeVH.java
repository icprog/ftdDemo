package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutRV.adapter.HeathAnalyzeAdapter;
import com.william.ftdui.widget.aboutRV.decoration.DotDecoration;

public class ReportHeathAnalyzeVH extends ReportBaseVH {

    private RecyclerView rv;
    private HeathAnalyzeAdapter adapter = new HeathAnalyzeAdapter();

    public ReportHeathAnalyzeVH(@NonNull View itemView) {
        super(itemView);
        rv = itemView.findViewById(R.id.rv);
        rv.addItemDecoration(new DotDecoration());
        rv.setAdapter(adapter);
    }

    @Override
    public void bind(@ReportFragment.ReportType int reportType, ReportBean bean) {
        AnalyzeResultBean analyzeResultBean = bean.getAnalyzeResultBean();
        if (analyzeResultBean != null && analyzeResultBean.getDataList() != null) {
            adapter.update(analyzeResultBean.getDataList());
        }
    }
}