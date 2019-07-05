package com.william.ftdui.widget.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.william.ftd_core.entity.DataBean;
import com.william.ftdui.R;
import com.william.zhibiaoview.ZhiBiaoView;

public class AnalyzeVH extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private ZhiBiaoView zb;
    private TextView tvDesc;

    public AnalyzeVH(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        zb = itemView.findViewById(R.id.zb);
        tvDesc = itemView.findViewById(R.id.tv_desc);
    }

    public void bind(DataBean bean){
        tvTitle.setText("●"+bean.getQuotaName());
        tvDesc.setText(bean.getQuotaDesc());
        zb.setNormalScore((int)(bean.getNormalScore()));
        zb.setScore((int)(bean.getScore()));
    }
}
