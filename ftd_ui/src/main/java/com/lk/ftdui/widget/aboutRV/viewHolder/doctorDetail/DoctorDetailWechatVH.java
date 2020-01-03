package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;


/**
 * 医生详情「微信群」
 *
 * R.layout.item_doctor_detail_wechat
 */
public class DoctorDetailWechatVH extends BaseVH {

    private ImageView iv;

    public DoctorDetailWechatVH(@NonNull View itemView) {
        super(itemView);

        iv = itemView.findViewById(R.id.iv);
    }
    @Override
    public void bind(DoctorDetailBean bean){
        Glide.with(iv).load(bean.getWechatCode()).into(iv);
    }
}
