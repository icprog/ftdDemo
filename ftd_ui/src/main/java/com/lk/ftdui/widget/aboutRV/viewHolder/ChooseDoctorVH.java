package com.lk.ftdui.widget.aboutRV.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lk.ftdui.R;

public class ChooseDoctorVH extends RecyclerView.ViewHolder {

    private ImageView iv;
    private TextView tvTitle;
    private TextView tvTags;
    private TextView tvContent;

    public ChooseDoctorVH(@NonNull View itemView) {
        super(itemView);

        iv = itemView.findViewById(R.id.iv);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvTags = itemView.findViewById(R.id.tv_tags);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    public void bind(){

    }
}
