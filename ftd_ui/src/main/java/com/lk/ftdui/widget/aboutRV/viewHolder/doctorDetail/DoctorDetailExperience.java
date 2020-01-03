package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;

/**
 * 医生详情「专业背景」
 *
 * R.layout.item_doctor_detail_experience
 */
public class DoctorDetailExperience extends BaseVH {

    private TextView tvTitle;
    private TextView tv_content;

    public DoctorDetailExperience(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvTitle.setText("专业背景");
        tv_content = itemView.findViewById(R.id.tv_experience);
    }
    @Override
    public void bind(DoctorDetailBean bean){
        tv_content.setText(bean.getExperience());
    }
}
