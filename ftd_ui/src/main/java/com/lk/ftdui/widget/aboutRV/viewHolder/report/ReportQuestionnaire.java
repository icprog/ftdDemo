package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;

import java.util.ArrayList;

public class ReportQuestionnaire extends ReportBaseVH {

    private TextView tv;

    public ReportQuestionnaire(@NonNull View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tv);
    }

    @Override
    public void bind(ReportType reportType, ReportBean bean) {
        if (bean.getUqe() == null){
            return;
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<ReportBean.UqeBean> list = (ArrayList<ReportBean.UqeBean>) bean.getUqe();
        for (ReportBean.UqeBean uqeBean : list) {
            if (TextUtils.isEmpty(uqeBean.getQuestionContent())){
                continue;
            }
            sb.append(uqeBean.getQuestionContent());
        }
        tv.setText(sb.toString());
    }
}
