package com.lk.ftdui.widget.aboutRV.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.entity.DataBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.adapter.TextAdapter;
import com.william.zhibiaoview.ZhiBiaoView;

public class AnalyzeVH extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private ZhiBiaoView zb;
    private TextView tvDesc;

    public AnalyzeVH(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tv_title);
        zb = itemView.findViewById(R.id.zb);
        tvDesc = itemView.findViewById(R.id.tv_desc);
    }

    public void bind(DataBean bean) {
        tvTitle.setText(bean.getQuotaName());
        setDesc(bean);
        zb.setNormalScore((int) (bean.getNormalScore()));
        zb.setScore((int) (bean.getScore()));
    }

    private void setDesc(DataBean bean) {
        String desc = bean.getQuotaDesc();
        int index = desc.indexOf('：') + 1;
        SpannableString ss = new SpannableString(desc);
        ForegroundColorSpan fcs = new ForegroundColorSpan(itemView.getContext().getResources().getColor(R.color.colorWrong));
        ss.setSpan(fcs, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDesc.setText(ss);
    }
}
