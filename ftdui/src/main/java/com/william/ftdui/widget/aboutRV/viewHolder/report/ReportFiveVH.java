package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.adapter.FiveAdapter;
import com.william.ftdui.widget.aboutRV.adapter.ReportAdapter;
import com.william.ftdui.widget.aboutRV.decoration.GridDividerItemDecoration;

public class ReportFiveVH extends ReportBaseVH {

    private FiveAdapter adapter;

    public ReportFiveVH(@NonNull View itemView) {
        super(itemView);
        adapter = new FiveAdapter();
        RecyclerView rv = itemView.findViewById(R.id.rv);
        rv.addItemDecoration(new GridDividerItemDecoration((GridLayoutManager) rv.getLayoutManager()));
        rv.setAdapter(adapter);
    }

    public void setListener(ReportAdapter.OnWuYangSelectListener listener) {
        this.adapter.setListener(listener);
    }

    @Override
    public void bind(ReportBean bean) {

    }


}
