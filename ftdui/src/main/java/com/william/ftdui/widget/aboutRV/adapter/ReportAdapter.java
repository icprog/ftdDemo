package com.william.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.william.ftd_core.entity.ReportBean;
import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResolutionVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResultVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportBaseVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportEightVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportFiveVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportHeathAnalyzeVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportIndexAnalyzeVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportProfessorVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportScoreVH;
import com.william.ftdui.widget.aboutRV.viewHolder.report.ReportTendencyVH;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportBaseVH> {

    private static final String TAG = "ReportAdapter";

    private ReportBean bean;

    private int reportType;

    private ArrayList<Pair> vhList = new ArrayList<>();

    private OnWuYangSelectListener listener;

    public ReportAdapter(@ReportFragment.ReportType int reportType, OnWuYangSelectListener listener) {
        this.reportType = reportType;
        this.listener = listener;

        if (reportType == ReportFragment.REPORT_CARD) {
            vhList.add(new Pair(R.layout.item_report_eight, ReportEightVH.class));
        } else {
            vhList.add(new Pair(R.layout.item_report_score, ReportScoreVH.class));
        }
        vhList.add(new Pair(R.layout.item_report_professor, ReportProfessorVH.class));
        vhList.add(new Pair(R.layout.item_report_analyze_resolution, ReportAnalysisResolutionVH.class));
        vhList.add(new Pair(R.layout.item_report_analyze_result, ReportAnalysisResultVH.class));
        vhList.add(new Pair(R.layout.item_report_tendency, ReportTendencyVH.class));
        vhList.add(new Pair(R.layout.item_report_heath_analyze, ReportHeathAnalyzeVH.class));
        vhList.add(new Pair(R.layout.item_report_index_analyze, ReportIndexAnalyzeVH.class));
        vhList.add(new Pair(R.layout.item_report_five, ReportFiveVH.class));
    }

    public void setData(ReportBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @NonNull
    @Override
    public ReportBaseVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ReportBaseVH vh = null;
        Pair pair = vhList.get(i);
        try {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(pair.layoutResId, viewGroup, false);
            Class<ReportBaseVH> clazz = (Class<ReportBaseVH>) pair.clazz;
            vh = clazz.getConstructor(View.class).newInstance(v);
        } catch (Exception e) {
            Log.e(TAG, "onCreateViewHolder: ", e);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportBaseVH viewHolder, int i) {
        if (bean != null) {
            viewHolder.bind(this.reportType,bean);
            if (i == 7) {
                ((ReportFiveVH) viewHolder).setListener(listener);
            }
        }
    }

    @Override
    public int getItemCount() {
        return vhList.size();
    }

    private static class Pair {
        int layoutResId;
        Class clazz;

        public Pair(int layoutResId, Class clazz) {
            this.layoutResId = layoutResId;
            this.clazz = clazz;
        }
    }

    public interface OnWuYangSelectListener {
        void onWuYangSelect(FiveAdapter.Five fiveBean);
    }
}
