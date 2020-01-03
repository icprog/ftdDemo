package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.BaseVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailCertificateVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailDomain;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailExperience;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailPhotoVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailPicturesVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailShopVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailTipVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.DoctorDetailWechatVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail.LkVH;

import java.util.ArrayList;
import java.util.Iterator;

public class DoctorDetailAdapter extends RecyclerView.Adapter<BaseVH> {

    private static final String TAG = "ReportAdapter";

    private static final int ID_PHOTO = 0;
    private static final int ID_TIP = 1;
    private static final int ID_CERTIFICATE = 2;
    private static final int ID_EXPERIENCE = 3;
    private static final int ID_DOMAIN = 4;
    private static final int ID_PIC = 5;
    private static final int ID_WEICHAT = 6;
    private static final int ID_SHOP = 7;
    private static final int LK = 8;

    private ArrayList<Triple> tripleList = new ArrayList<>();

    private DoctorDetailBean bean;


    private void checkDoctorDetail(DoctorDetailBean bean) {
        tripleList.clear();
        if (bean.getMemberDTO() != null) {
            tripleList.add(new Triple(ID_PHOTO, R.layout.item_doctor_detail_photo, DoctorDetailPhotoVH.class));
        }
        if (!TextUtils.isEmpty(bean.getHeartLanguage())) {
            tripleList.add(new Triple(ID_TIP, R.layout.item_doctor_detail_tip, DoctorDetailTipVH.class));
        }
        if (!TextUtils.isEmpty(bean.getLicenseImage())) {
            tripleList.add(new Triple(ID_CERTIFICATE, R.layout.item_doctor_detail_certificate, DoctorDetailCertificateVH.class));
        }
        if (!TextUtils.isEmpty(bean.getExperience())) {
            tripleList.add(new Triple(ID_EXPERIENCE, R.layout.item_doctor_detail_experience, DoctorDetailExperience.class));
        }
        if (!TextUtils.isEmpty(bean.getDomain())) {
            tripleList.add(new Triple(ID_DOMAIN, R.layout.item_doctor_detail_experience, DoctorDetailDomain.class));
        }
        if (bean.getMemberDoctorPicPoList() != null && !bean.getMemberDoctorPicPoList().isEmpty()) {
            tripleList.add(new Triple(ID_PIC, R.layout.item_doctor_detail_pictures, DoctorDetailPicturesVH.class));
        }
        if (!TextUtils.isEmpty(bean.getWechatCode())) {
            tripleList.add(new Triple(ID_WEICHAT, R.layout.item_doctor_detail_wechat, DoctorDetailWechatVH.class));
        }
        if (bean.getShop() != null) {
            tripleList.add(new Triple(ID_SHOP, R.layout.item_doctor_detail_shop, DoctorDetailShopVH.class));
        }
        tripleList.add(new Triple(LK, R.layout.item_lk, LkVH.class));
    }

    /**
     * 更新数据
     *
     * @param bean
     */
    public void updateData(DoctorDetailBean bean) {
        this.bean = bean;
        checkDoctorDetail(bean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return tripleList.get(position).plate;
    }

    @NonNull
    @Override
    public BaseVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Triple triple = null;
        for (Triple t : tripleList) {
            if(t.plate == i){
                triple = t;
                break;
            }
        }
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(triple.layoutResId, viewGroup, false);
        BaseVH vh = null;
        try {
            vh = triple.clazz.getConstructor(View.class).newInstance(view);
        } catch (Exception e) {
            Log.e(TAG, "onCreateViewHolder: ", e);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVH viewHolder, int i) {
        if (bean != null) {
            viewHolder.bind(bean);
        }
    }

    @Override
    public int getItemCount() {
        return tripleList.size();
    }


    private static class Triple {
        int plate;
        int layoutResId;
        Class<? extends BaseVH> clazz;

        public Triple(int plate, int layoutResId, Class<? extends BaseVH> clazz) {
            this.plate = plate;
            this.layoutResId = layoutResId;
            this.clazz = clazz;
        }
    }
}
