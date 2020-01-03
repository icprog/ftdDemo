package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.DoctorDiagnoseBean;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;

public class ReportDoctorVH extends ReportBaseVH {

    private ImageView iv;
    private TextView tvName;
    private TextView tvDateTime;
    private TextView tvContent;

    public ReportDoctorVH(@NonNull View itemView) {
        super(itemView);

        iv = itemView.findViewById(R.id.iv);
        tvName = itemView.findViewById(R.id.tv_name);
        tvDateTime = itemView.findViewById(R.id.tv_date_time);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

    @Override
    public void bind(ReportType reportType, ReportBean bean) {
        DoctorDiagnoseBean doctor = bean.getDoctorDiagnoseBean();
        if (doctor == null){
            return;
        }
        Glide.with(iv).load(doctor.getDoctorImgUrl()).into(iv);
        tvName.setText(doctor.getDoctorName());
        tvDateTime.setText(doctor.getCreateTime());
        tvContent.setText(doctor.getCustomAftercare());
    }
}
