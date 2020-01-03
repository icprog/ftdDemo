package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;

/**
 * 医生详情「店铺信息」
 *
 * R.layout.item_doctor_detail_shop
 */
public class DoctorDetailShopVH extends BaseVH {

    private TextView tvName;
    private TextView tvAddress;
    private ImageView iv;

    public DoctorDetailShopVH(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tv_name);
        tvAddress = itemView.findViewById(R.id.tv_address);
        iv = itemView.findViewById(R.id.iv_shop);
    }
    @Override
    public void bind(DoctorDetailBean bean){
        DoctorDetailBean.ShopBean shop = bean.getShop();
        if (shop != null) {
            tvName.setText(bean.getShop().getShopName());
            tvAddress.setText(bean.getShop().getShopAddress());
            Glide.with(iv).load(bean.getShop().getShopLogo()).into(iv);
        }
    }
}
