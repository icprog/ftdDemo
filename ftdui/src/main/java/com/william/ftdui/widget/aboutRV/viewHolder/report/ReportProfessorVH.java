package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;

/**
 * 专家点评
 */
public class ReportProfessorVH extends ReportBaseVH {

    private TextView tv;
    private ImageView ivFace;
    private ImageView ivTongueTop;

    public ReportProfessorVH(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv_content);
        ivFace = itemView.findViewById(R.id.iv_face);
        ivTongueTop = itemView.findViewById(R.id.iv_tongue_top);
    }

    @Override
    public void bind(ReportBean bean) {
        AnalyzeResultBean analyzeResult = bean.getAnalyzeResultBean();
        if (analyzeResult != null && !TextUtils.isEmpty(analyzeResult.getOpinion())) {
            tv.setText(analyzeResult.getOpinion());
        }
        Glide.with(ivFace).load(bean.getFaceImg()).into(ivFace);
        Glide.with(ivTongueTop).load(bean.getTongueImg()).into(ivTongueTop);
    }
}
