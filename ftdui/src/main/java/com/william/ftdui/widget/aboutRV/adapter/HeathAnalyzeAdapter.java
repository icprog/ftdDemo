package com.william.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.viewHolder.report.HeathAnalyzeSubVH;

import java.util.ArrayList;
import java.util.Collection;

public class HeathAnalyzeAdapter extends RecyclerView.Adapter<HeathAnalyzeSubVH> {

    private ArrayList<AnalyzeResultBean.Data> dataList = new ArrayList<>(10);

    public void update(Collection<AnalyzeResultBean.Data> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeathAnalyzeSubVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_heath_analyze_sub, viewGroup, false);
        return new HeathAnalyzeSubVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeathAnalyzeSubVH viewHolder, int i) {
        viewHolder.bind(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
