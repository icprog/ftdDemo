package com.william.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.william.ftd_core.entity.DataBean;
import com.william.ftd_core.entity.QuotaInfoListBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutRV.adapter.IndexAnalyzeAdapter;
import com.william.ftdui.widget.aboutRV.decoration.DotDecoration;

import java.util.ArrayList;
import java.util.List;

public class ReportIndexAnalyzeVH extends ReportBaseVH {

    private TextView tvCounter;
    private IndexAnalyzeAdapter adapter = new IndexAnalyzeAdapter();

    private static final String counterReg = "（共%d项，%d项异常）";

    public ReportIndexAnalyzeVH(@NonNull View itemView) {
        super(itemView);

        tvCounter = itemView.findViewById(R.id.tv_counter);
        RecyclerView rv = itemView.findViewById(R.id.rv);
        rv.addItemDecoration(new DotDecoration());
        rv.setAdapter(adapter);
    }

    @Override
    public void bind(@ReportFragment.ReportType int reportType, ReportBean bean) {
        ArrayList<QuotaInfoListBean> quotaInfoList = (ArrayList) bean.getQuotaInfoList();
        List<DataBean> exceptionList = new ArrayList<DataBean>();
        List<DataBean> datas;
        int totalCount = 0;
        int exceptionCount = 0;
        for (QuotaInfoListBean quotaInfo : quotaInfoList) {
            datas = quotaInfo.getData();
            if (datas != null) {
                for (DataBean data : datas) {
                    if (data.getScore() < data.getNormalScore()) {
                        exceptionList.add(data);
                        exceptionCount++;
                    }
                    totalCount++;
                }
            }
        }
        String counterContent = String.format(counterReg, totalCount, exceptionCount);
        SpannableString ss = new SpannableString(counterContent);
        ForegroundColorSpan fcs = new ForegroundColorSpan(itemView.getContext().getResources().getColor(R.color.colorWrong));
        int start = counterContent.indexOf('，') + 1;
        int end = counterContent.lastIndexOf('）');
        ss.setSpan(fcs, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvCounter.setText(ss);

        if (bean.getQuotaInfoList() != null) {
            adapter.update(exceptionList);
        }
    }

}
