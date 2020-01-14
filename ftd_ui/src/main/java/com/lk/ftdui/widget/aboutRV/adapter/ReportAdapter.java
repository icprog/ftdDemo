package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftd_core.entity.ReportBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResolutionVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportAnalysisResultVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportBaseVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportDoctorVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportFiveVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportHeathAnalyzeVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportIndexAnalyzeVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportProfessorVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportQuestionnaire;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportScoreVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportShangVH;
import com.lk.ftdui.widget.aboutRV.viewHolder.report.ReportTendencyVH;
import com.william.zhibiaoview.DashBoardView;

import java.util.ArrayList;

import static com.lk.ftd_core.constant.ReportType.REPORT_CARD;


public class ReportAdapter extends RecyclerView.Adapter<ReportBaseVH> {


    private static final String TAG = "ReportAdapter";

    private ReportBean bean;

    private ReportType reportType;

    private ArrayList<Plate> vhList = new ArrayList<>();

    private OnWuYangSelectListener onWuYangSelectListener;
    private DashBoardView.OnQuestionMarkClickListener onQuestionMarkClickListener;

    private double constitutionScore;

    public ReportAdapter(ReportType reportType, OnWuYangSelectListener onWuYangSelectListener, DashBoardView.OnQuestionMarkClickListener onQuestionMarkClickListener) {
        this.reportType = reportType;
        this.onWuYangSelectListener = onWuYangSelectListener;
        this.onQuestionMarkClickListener = onQuestionMarkClickListener;
        if (reportType == REPORT_CARD) {
            loadCard();
        } else {
            loadConstitution();
        }
    }

    private enum Plate {
        DASH_BOARD(R.layout.item_report_entropy_dashboard, ReportShangVH.class),
        SCORE(R.layout.item_report_score, ReportScoreVH.class),
        PHOTOS(R.layout.item_report_photos, ReportProfessorVH.class),
        QUESTIONNAIRE(R.layout.item_report_questionnaire, ReportQuestionnaire.class),
        DOCTOR(R.layout.item_report_doctor, ReportDoctorVH.class),
        RESOLUTION(R.layout.item_report_analyze_resolution, ReportAnalysisResolutionVH.class),
        RESULT(R.layout.item_report_analyze_result, ReportAnalysisResultVH.class),
//        TENDENCY(R.layout.item_report_tendency, ReportTendencyVH.class),
        HEATH(R.layout.item_report_heath_analyze, ReportHeathAnalyzeVH.class),
        INDEX(R.layout.item_report_index_analyze, ReportIndexAnalyzeVH.class),
        FIVE(R.layout.item_report_five, ReportFiveVH.class);
        @LayoutRes
        int layoutResID;
        Class<? extends ReportBaseVH> clazz;

        Plate(int layoutResID, Class<? extends ReportBaseVH> clazz) {
            this.layoutResID = layoutResID;
            this.clazz = clazz;
        }
    }

    private void loadCard() {
        vhList.add(Plate.DASH_BOARD);
        vhList.add(Plate.PHOTOS);
        vhList.add(Plate.DOCTOR);
        vhList.add(Plate.QUESTIONNAIRE);
        vhList.add(Plate.RESOLUTION);
        vhList.add(Plate.RESULT);
//        vhList.add(Plate.TENDENCY);
        vhList.add(Plate.HEATH);
        vhList.add(Plate.INDEX);
        vhList.add(Plate.FIVE);
    }

    private void loadConstitution() {
        vhList.add(Plate.SCORE);
        vhList.add(Plate.RESOLUTION);
        vhList.add(Plate.RESULT);
//        vhList.add(Plate.TENDENCY);
        vhList.add(Plate.HEATH);
        vhList.add(Plate.INDEX);
        vhList.add(Plate.FIVE);
    }

    public void setData(ReportBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void notifyItem(Plate plate) {
        int plateIndex = vhList.indexOf(plate);
        notifyItemChanged(plateIndex);
    }

    /**
     * 修改体质的健康分数为体证的得分
     *
     * @param score
     */
    public void setScore(double score) {
        this.constitutionScore = score;
        notifyItem(Plate.SCORE);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @NonNull
    @Override
    public ReportBaseVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ReportBaseVH vh = null;
        Plate plate = vhList.get(i);
        try {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(plate.layoutResID, viewGroup, false);
            vh = plate.clazz.getConstructor(View.class).newInstance(v);
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

        if (vhList.get(i) == Plate.DASH_BOARD) {
            ((ReportShangVH) viewHolder).setListener(onQuestionMarkClickListener);
        }

        if (vhList.get(i) == Plate.SCORE) {
            ((ReportScoreVH) viewHolder).setScore(constitutionScore);
        }

        if (vhList.get(i) == Plate.FIVE) {
            ((ReportFiveVH) viewHolder).setListener(onWuYangSelectListener);
        }

    }

    @Override
    public int getItemCount() {
        return vhList.size();
    }

    public interface OnWuYangSelectListener {
        void onWuYangSelect(FiveAdapter.Five fiveBean);
    }
}
