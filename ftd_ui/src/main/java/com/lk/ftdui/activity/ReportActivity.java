package com.lk.ftdui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lk.ftd_core.constant.ReportType;
import com.lk.ftdui.activity.param.DoctorInfo;
import com.lk.ftdui.widget.aboutVP.ReprotFragmentAdapter;
import com.lk.ftdui.R;
import com.lk.ftdui.fragment.ReportFragment;

public class ReportActivity extends BaseActivity implements ReportFragment.Listener {

    private TabLayout tl;
    private ViewPager vp;
    private ReprotFragmentAdapter adapter;

    public static void start(Activity activity, String cardSeqNo, String constitutionSeqNo) {
        Intent intent = new Intent(activity, ReportActivity.class);
        intent.putExtra("cardSeqNo", cardSeqNo);
        intent.putExtra("constitutionSeqNo", constitutionSeqNo);
        activity.startActivity(intent);
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        String cardSeqNo = intent.getStringExtra("cardSeqNo");//, "2019080210382300373L"
        String constitutionSeqNo = intent.getStringExtra("constitutionSeqNo");//2019080210382300374L
        DoctorInfo DoctorInfo = intent.getParcelableExtra("doctorInfo");
        ReportFragment.ReprotFragmentParam cardParam = new ReportFragment.ReprotFragmentParam(ReportType.REPORT_CARD.ordinal(), cardSeqNo, DoctorInfo);
        ReportFragment.ReprotFragmentParam constitutionParam = new ReportFragment.ReprotFragmentParam(ReportType.REPORT_CONSTITUTION.ordinal(), constitutionSeqNo, DoctorInfo);
        adapter = new ReprotFragmentAdapter(getSupportFragmentManager(), cardParam, constitutionParam);
        vp = findViewById(R.id.vp);
        vp.setAdapter(adapter);
        tl = findViewById(R.id.tl);
        tl.setupWithViewPager(vp);
    }

    @Override
    protected String setTitle() {
        return "报告详情";
    }

    @Override
    public int setContentViewResId() {
        return R.layout.activity_report;
    }

    @Override
    public void onGetScore(double score) {
        adapter.setScore(score);
    }
}