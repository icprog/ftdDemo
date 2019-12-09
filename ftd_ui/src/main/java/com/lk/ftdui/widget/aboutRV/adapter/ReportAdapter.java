package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResolutionVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResultVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportBaseVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportEightVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportFiveVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportHeathAnalyzeVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportIndexAnalyzeVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportProfessorVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportScoreVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportShangVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportTendencyVH;

import java.util.ArrayList;


public class ReportAdapter extends RecyclerView.Adapter<ReportBaseVH> {

    private static final String TAG = "ReportAdapter";

    private ReportBean bean;

    private int reportType;

    private ArrayList<Pair> vhList = new ArrayList<>();

    private OnWuYangSelectListener listener;

    private double constitutionScore;

    public ReportAdapter(@ReportFragment.ReportType int reportType, OnWuYangSelectListener listener) {
        this.reportType = reportType;
        this.listener = listener;

        if (reportType == ReportFragment.REPORT_CARD) {
            loadCard();
        } else {
            loadConstitution();
        }
    }


    private void loadCard() {
//        vhList.add(new Pair(ReportFragment.EIGHT, R.layout.item_report_eight, ReportEightVH.class));
        vhList.add(new Pair(ReportFragment.EIGHT, R.layout.item_report_entropy_dashboard, ReportShangVH.class));
        vhList.add(new Pair(ReportFragment.PROFESSOR, R.layout.item_report_professor, ReportProfessorVH.class));
        vhList.add(new Pair(ReportFragment.RESOLUTION, R.layout.item_report_analyze_resolution, ReportAnalysisResolutionVH.class));
        vhList.add(new Pair(ReportFragment.RESULT, R.layout.item_report_analyze_result, ReportAnalysisResultVH.class));
        vhList.add(new Pair(ReportFragment.TENDENCY, R.layout.item_report_tendency, ReportTendencyVH.class));
        vhList.add(new Pair(ReportFragment.HEATH, R.layout.item_report_heath_analyze, ReportHeathAnalyzeVH.class));
        vhList.add(new Pair(ReportFragment.INDEX, R.layout.item_report_index_analyze, ReportIndexAnalyzeVH.class));
        vhList.add(new Pair(ReportFragment.FIVE, R.layout.item_report_five, ReportFiveVH.class));
    }

    private void loadConstitution() {
        vhList.add(new Pair(ReportFragment.SCORE, R.layout.item_report_score, ReportScoreVH.class));
        vhList.add(new Pair(ReportFragment.RESOLUTION, R.layout.item_report_analyze_resolution, ReportAnalysisResolutionVH.class));
        vhList.add(new Pair(ReportFragment.RESULT, R.layout.item_report_analyze_result, ReportAnalysisResultVH.class));
        vhList.add(new Pair(ReportFragment.TENDENCY, R.layout.item_report_tendency, ReportTendencyVH.class));
        vhList.add(new Pair(ReportFragment.HEATH, R.layout.item_report_heath_analyze, ReportHeathAnalyzeVH.class));
        vhList.add(new Pair(ReportFragment.INDEX, R.layout.item_report_index_analyze, ReportIndexAnalyzeVH.class));
        vhList.add(new Pair(ReportFragment.FIVE, R.layout.item_report_five, ReportFiveVH.class));
    }

    public void setData(ReportBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void notifyItem(@ReportFragment.Plate int plate) {
        int size = vhList.size();
        for (int i = 0; i < size; i++) {
            if (vhList.get(i).plate == plate) {
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * 修改体质的健康分数为体证的得分
     *
     * @param score
     */
    public void setScore(double score) {
        this.constitutionScore = score;
        notifyItem(ReportFragment.SCORE);
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
        if (bean == null) {
            return;
        }
        viewHolder.bind(this.reportType, bean);

        if (vhList.get(i).plate == ReportFragment.SCORE){
            ((ReportScoreVH) viewHolder).setScore(constitutionScore);
        }

        if (vhList.get(i).plate == ReportFragment.FIVE) {
            ((ReportFiveVH) viewHolder).setListener(listener);
        }

    }

    @Override
    public int getItemCount() {
        return vhList.size();
    }

    private static class Pair {
        int plate;
        int layoutResId;
        Class clazz;

        public Pair(@ReportFragment.Plate int plate, int layoutResId, Class clazz) {
            this.plate = plate;
            this.layoutResId = layoutResId;
            this.clazz = clazz;
        }
    }

    public interface OnWuYangSelectListener {
        void onWuYangSelect(FiveAdapter.Five fiveBean);
    }
}
