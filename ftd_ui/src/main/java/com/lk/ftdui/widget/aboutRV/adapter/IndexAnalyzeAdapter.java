package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.entity.DataBean;
import com.lk.ftd_core.entity.QuotaInfoListBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.viewHolder.AnalyzeVH;

import java.util.ArrayList;
import java.util.List;

public class IndexAnalyzeAdapter extends RecyclerView.Adapter<AnalyzeVH> {

    private List<DataBean> dataList = new ArrayList<>();

    public void update(List<DataBean> list) {
        dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    private void process(List<QuotaInfoListBean> list) {
        dataList.clear();
        List<DataBean> datas;
        for (QuotaInfoListBean quotaInfo : list) {
            datas = quotaInfo.getData();
            if (datas != null) {
                for (DataBean data : datas) {
                    if (data.getScore() < data.getNormalScore()) {
                        dataList.add(data);
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public AnalyzeVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_analyze, viewGroup, false);
        return new AnalyzeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyzeVH analyzeVH, int i) {
        analyzeVH.bind(dataList.get(i));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
