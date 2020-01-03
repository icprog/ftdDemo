package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.AnalyzeResultBean;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;
import com.lk.ftdui.widget.aboutRV.adapter.HeathAnalyzeAdapter;
import com.lk.ftdui.widget.aboutRV.decoration.DotDecoration;

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
    public void bind(ReportType reportType, ReportBean bean) {
        AnalyzeResultBean analyzeResultBean = bean.getAnalyzeResultBean();
        if (analyzeResultBean != null && analyzeResultBean.getList() != null) {
            adapter.update(analyzeResultBean.getList());
        }
    }
}