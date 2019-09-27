package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutVP.ReprotFragmentAdapter;

public class ReportActivity extends BaseActivity implements ReportFragment.Listener {

    private TabLayout tl;
    private ViewPager vp;
    private ReprotFragmentAdapter adapter;

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        long cardSeqNo = intent.getLongExtra("cardSeqNo", 2019080210382300373L);//todo
        long constitutionSeqNo = intent.getLongExtra("constitutionSeqNo", 2019080210382300374L);
        ReportFragment.ReprotFragmentParam cardParam = new ReportFragment.ReprotFragmentParam(ReportFragment.REPORT_CARD, cardSeqNo);
        ReportFragment.ReprotFragmentParam constitutionParam = new ReportFragment.ReprotFragmentParam(ReportFragment.REPORT_CONSTITUTION, constitutionSeqNo);
        adapter = new ReprotFragmentAdapter(getSupportFragmentManager(), cardParam, constitutionParam);
        vp = findViewById(R.id.vp);
        vp.setAdapter(adapter);
        tl = findViewById(R.id.tl);
        tl.setupWithViewPager(vp);
    }

    @Override
    protected String setTitle() {
        return "报告详情  ";
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