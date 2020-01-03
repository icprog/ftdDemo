package com.lk.ftdui.widget.aboutRV.viewHolder.report;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.QuotaInfoListBean;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;
import com.lk.ftdui.widget.aboutRV.adapter.TextAdapter;
import com.lk.ftdui.widget.aboutRV.decoration.DotDecoration;

import java.util.List;

public class ReportAnalysisResultVH extends ReportBaseVH {

    private RecyclerView rv;
    private TextAdapter adapter = new TextAdapter();

    public ReportAnalysisResultVH(@NonNull View itemView) {
        super(itemView);
        rv = itemView.findViewById(R.id.rv);
        rv.addItemDecoration(new DotDecoration());
        rv.setAdapter(adapter);
    }

    @Override
    public void bind(ReportType reportType, ReportBean bean) {
//        ReportBean.FaceDiagnoseBean fdb = bean.getFaceDiagnose();
//        if (fdb != null) {
//            LinkedList<String> list = new LinkedList<>();
//            list.add(fdb.getFace());
//            list.add(fdb.getTongue());
//            list.add(fdb.getMoss());
//            adapter.update(list);
//        }

        List<QuotaInfoListBean> fdb = bean.getQuotaInfoList();
        if (fdb != null) {
//            LinkedList<QuotaInfoListBean> list = new LinkedList<>();
//            list.add(fdb.getFace());
//            list.add(fdb.getTongue());
//            list.add(fdb.getMoss());
            adapter.update(fdb);
        }
    }
}
