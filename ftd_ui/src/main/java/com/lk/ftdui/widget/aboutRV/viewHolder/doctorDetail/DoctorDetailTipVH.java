package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;

/**
 * 医生详情 「健康心语」一栏
 *
 * R.layout.item_doctor_detail_tip
 */
public class DoctorDetailTipVH extends BaseVH {

    private TextView tv;
    public DoctorDetailTipVH(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv_tip);
    }
    @Override
    public void bind(DoctorDetailBean bean){
        tv.setText(bean.getHeartLanguage());
    }
}
