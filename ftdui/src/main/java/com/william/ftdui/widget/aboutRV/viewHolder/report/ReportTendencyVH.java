package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.view.View;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftdui.R;
import com.william.zhibiaoview.ZheXianView;

import java.util.LinkedList;
import java.util.List;

public class ReportTendencyVH extends ReportBaseVH {

    private ZheXianView zheXianView;

    public ReportTendencyVH(@NonNull View itemView) {
        super(itemView);
        zheXianView = itemView.findViewById(R.id.zhexian);
    }

    @Override
    public void bind(ReportBean bean) {

        if (bean.getTendencyResult() != null) {
            List<TendencyResult.ResultBean> list = bean.getTendencyResult().getResult();
            LinkedList<ZheXianView.Data> dataSeries = new LinkedList<>();
            if (list != null) {
                int size = list.size();
                for (int i = size - 1; i >= 0; i--) {
                    dataSeries.add(new ZheXianView.Data(dateFormate(list.get(i).getDate()), (float) list.get(i).getScore()));
                }
                zheXianView.initData(dataSeries);
            }
        }
    }

    private String dateFormate(String date) {
        String[] strs = date.split("-");
        StringBuilder sb = new StringBuilder(strs[1]);
        sb.append(".");
        sb.append(strs[2]);
        return sb.toString();
    }
}
