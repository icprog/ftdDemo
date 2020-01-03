package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lk.ftd_core.entity.DoctorDetailBean;

public abstract class BaseVH extends RecyclerView.ViewHolder {

    public BaseVH(@NonNull View itemView) {
        super(itemView);
    }

    abstract public void bind(DoctorDetailBean bean);
}
