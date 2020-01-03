package com.lk.ftdui.widget.aboutRV.viewHolder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.entity.RecordListBean;
import com.lk.ftdui.R;

/**
 * 检测记录的item
 *
 * @layout R.layout.item_record
 */
public class RecordVH extends RecyclerView.ViewHolder {

    private TextView tvDateTime;
    private TextView tvName;
    private TextView tvState;
    private AppCompatImageView iv;

    public RecordVH(@NonNull View itemView) {
        super(itemView);

        tvDateTime = itemView.findViewById(R.id.tv_date_time);
        tvName = itemView.findViewById(R.id.tv_name);
        tvState = itemView.findViewById(R.id.tv_state);
        iv = itemView.findViewById(R.id.iv);
    }

    public void bind(RecordListBean.RecordBean bean) {
        tvDateTime.setText(bean.getDetectTime());
        tvName.setText(bean.getDoctorName());

        Context context = itemView.getContext();
        Resources resource = context.getResources();
        String stateText;
        int drawableId;
        int color;
        switch (bean.getIsDeal()) {
            case RecordListBean.RecordBean.done:
                stateText = "已完成";
                drawableId = R.drawable.result_correct;
                color = resource.getColor(R.color.colorPrimary);
                break;
            case RecordListBean.RecordBean.doing:
                stateText = "评估中";
                drawableId = R.drawable.doing;
                color = Color.parseColor("#FE9100");
                break;
            default:
                stateText = "未评估";
                drawableId = R.drawable.result_warning;
                color = Color.parseColor("#EE5544");
        }
        tvState.setText(stateText);
        tvState.setTextColor(color);
        tvState.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
        iv.setImageTintList(ColorStateList.valueOf(color));
    }
}
