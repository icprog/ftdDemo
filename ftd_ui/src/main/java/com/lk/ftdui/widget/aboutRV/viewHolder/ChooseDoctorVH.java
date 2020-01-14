package com.lk.ftdui.widget.aboutRV.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftd_core.entity.DoctorListBean;
import com.lk.ftd_core.entity.DoctorTag;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.adapter.DoctorListAdapter;
import com.lk.mogaijson.JSONObject;


import java.util.List;

public class ChooseDoctorVH extends RecyclerView.ViewHolder {

    private ImageView iv;
    private TextView tvTitle;
    private TextView tvTags;
    private TextView tvContent;
    private Button btn;

    public ChooseDoctorVH(@NonNull View itemView) {
        super(itemView);

        iv = itemView.findViewById(R.id.iv);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvTags = itemView.findViewById(R.id.tv_tags);
        tvContent = itemView.findViewById(R.id.tv_content);
        this.btn = itemView.findViewById(R.id.btn);
    }

//    public void bind(final DoctorListBean.DoctorBean bean, final DoctorListAdapter.OnSelectedListener listener) {
//        tvTitle.setText(bean.getMemberDTO().getTrueName());
//        tvContent.setText(bean.getDomain());
//        List<DoctorTag> tagList = JSONObject.parseArray(bean.getTags(),DoctorTag.class);
//        if (tagList != null && !tagList.isEmpty()) {
//            StringBuilder sb = new StringBuilder();
//            for (DoctorTag tag : tagList) {
//                sb.append(tag.getTagName());
//                sb.append("\t");
//            }
//            tvTags.setText(sb.toString());
//        }
//        Glide.with(iv).load(bean.getMemberDTO().getMemberHeadUrl()).into(iv);
//        this.btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onDoctorChoosed(bean);
//            }
//        });
//    }

    public void bind(final DoctorDetailBean bean, final DoctorListAdapter.OnSelectedListener listener) {
        tvTitle.setText(bean.getMemberDTO().getTrueName());
        tvContent.setText(bean.getDomain());
//        List<DoctorTag> tagList = JSONObject.parseArray(bean.getTags(),DoctorTag.class);
        List<DoctorTag> tagList = bean.getTags();
        if (tagList != null && !tagList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (DoctorTag tag : tagList) {
                sb.append(tag.getTagName());
                sb.append("\t");
            }
            tvTags.setText(sb.toString());
        }
        Glide.with(iv).load(bean.getMemberDTO().getMemberHeadUrl()).into(iv);
        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDoctorChoosed(bean);
            }
        });
    }
}
