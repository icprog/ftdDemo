package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftd_core.entity.DoctorTag;
import com.lk.ftdui.R;

import java.util.List;

/**
 * 医生详情头像一栏
 *
 * R.layout.item_doctor_detail_photo
 */
public class DoctorDetailPhotoVH extends BaseVH {

    private ImageView ivPhoto;
    private TextView tvName;
    private TextView tvTags;
    private TextView tvWatchCount;
    private ImageView ivQRCode;

    public DoctorDetailPhotoVH(@NonNull View itemView) {
        super(itemView);

        ivPhoto = itemView.findViewById(R.id.iv_photo);
        tvName = itemView.findViewById(R.id.tv_name);
        tvTags = itemView.findViewById(R.id.tv_tags);
        tvWatchCount = itemView.findViewById(R.id.tv_watched_count);
        ivQRCode = itemView.findViewById(R.id.iv_qrcode);
    }
    @Override
    public void bind(DoctorDetailBean bean){
        tvName.setText(bean.getMemberDTO().getTrueName());
        tvWatchCount.setText(String.valueOf(bean.getAttentionNum()));
        Glide.with(ivPhoto).load(bean.getMemberDTO().getMemberHeadUrl()).placeholder(R.drawable.photo_place).into(ivPhoto);
        Glide.with(ivQRCode).load(bean.getQuCode()).into(ivQRCode);
        List<DoctorTag> tagList = bean.getTags();
        if (tagList != null && !tagList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (DoctorTag tag : tagList) {
                sb.append(tag.getTagName());
                sb.append("\t");
            }
            tvTags.setText(sb.toString());
        }
    }

}
