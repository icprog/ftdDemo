package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;

/**
 * 医生详情「技能证书」一栏
 *
 * R.layout.item_doctor_detail_certificate
 */
public class DoctorDetailCertificateVH extends BaseVH {

    private ImageView iv;

    public DoctorDetailCertificateVH(@NonNull View itemView) {
        super(itemView);
        iv = itemView.findViewById(R.id.iv_certificate);
    }

    @Override
    public void bind(DoctorDetailBean bean){
        Glide.with(iv).load(bean.getLicenseImage()).into(iv);
    }
}
