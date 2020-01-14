package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.AnalyzeResultBean;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;

/**
 * 专家点评
 */
public class ReportProfessorVH extends ReportBaseVH {

    private ImageView ivFace;
    private ImageView ivTongueTop;
    private ImageView ivTongueBottom;

    public ReportProfessorVH(@NonNull View itemView) {
        super(itemView);
        ivFace = itemView.findViewById(R.id.iv_face);
        ivTongueTop = itemView.findViewById(R.id.iv_tongue_top);
        ivTongueBottom = itemView.findViewById(R.id.iv_tongue_bottom);
    }

    @Override
    public void bind(ReportType reportType, ReportBean bean) {
        Glide.with(ivFace).load(bean.getFaceImg()).into(ivFace);
        Glide.with(ivTongueTop).load(bean.getTongueImg()).into(ivTongueTop);
        Glide.with(ivTongueBottom).load(bean.getBaseOfTongueUrl()).into(ivTongueBottom);
    }
}
