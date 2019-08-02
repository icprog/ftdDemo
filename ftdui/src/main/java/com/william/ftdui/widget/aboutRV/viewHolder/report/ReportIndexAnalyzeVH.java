package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutRV.adapter.IndexAnalyzeAdapter;
import com.william.ftdui.widget.aboutRV.decoration.DotDecoration;

public class ReportIndexAnalyzeVH extends ReportBaseVH {

    private IndexAnalyzeAdapter adapter = new IndexAnalyzeAdapter();

    public ReportIndexAnalyzeVH(@NonNull View itemView) {
        super(itemView);

        RecyclerView rv = itemView.findViewById(R.id.rv);
        rv.addItemDecoration(new DotDecoration());
        rv.setAdapter(adapter);
    }

    @Override
    public void bind(@ReportFragment.ReportType int reportType, ReportBean bean) {
        if ( bean.getQuotaInfoList() != null) {
            adapter.update(bean.getQuotaInfoList());
        }
    }
}
