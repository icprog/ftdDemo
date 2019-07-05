package com.william.ftdui.widget.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.william.ftd_core.entity.DataBean;
import com.william.ftd_core.entity.QuotaInfoListBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.adapter.viewHolder.AnalyzeVH;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeAdapter extends RecyclerView.Adapter<AnalyzeVH> {

    private List<DataBean> dataList = new ArrayList<DataBean>();

    public AnalyzeAdapter(List<QuotaInfoListBean> list) {
        process(list);
    }

    public void setData(List<QuotaInfoListBean> list) {
        process(list);
        notifyDataSetChanged();
    }

    private void process(List<QuotaInfoListBean> list){
        List<DataBean> datas;
        for (QuotaInfoListBean quotaInfo : list) {
            datas = quotaInfo.getData();
            for (DataBean data : datas) {
                if (data.getScore() < data.getNormalScore()) {
                    dataList.add(data);
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
