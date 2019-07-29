package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.adapter.TextAdapter;
import com.william.ftdui.widget.aboutRV.decoration.DotDecoration;

import java.util.LinkedList;

public class ReportAnalysisResultVH extends ReportBaseVH {

    private RecyclerView rv;
    private TextAdapter adapter = new TextAdapter();

    public ReportAnalysisResultVH(@NonNull View itemView) {
        super(itemView);
        rv = itemView.findViewById(R.id.rv);
        // todo 加圆点
        rv.addItemDecoration(new DotDecoration());
        rv.setAdapter(adapter);
    }

    @Override
    public void bind(ReportBean bean) {
        ReportBean.FaceDiagnoseBean fdb = bean.getFaceDiagnose();
        if (fdb != null) {
            LinkedList<String> list = new LinkedList<>();
            list.add(fdb.getFace());
            list.add(fdb.getTongue());
            list.add(fdb.getMoss());
            adapter.update(list);
        }
    }
}
