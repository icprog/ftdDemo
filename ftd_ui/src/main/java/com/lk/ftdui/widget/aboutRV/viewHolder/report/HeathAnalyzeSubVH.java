package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.entity.AnalyzeResultBean;
import com.lk.ftdui.R;

public class HeathAnalyzeSubVH extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvContent;

    public HeathAnalyzeSubVH(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    public void bind(AnalyzeResultBean.Data data){
        tvTitle.setText(data.getName());
        tvContent.setText(data.getIntro());
    }
}
