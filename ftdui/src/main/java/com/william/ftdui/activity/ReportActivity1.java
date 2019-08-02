package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.william.ftdui.R;
import com.william.ftdui.fragment.ReportFragment;
import com.william.ftdui.widget.aboutVP.ReprotFragmentAdapter;

public class ReportActivity1 extends BaseActivity {

    private TabLayout tl;
    private ViewPager vp;
    private ReprotFragmentAdapter adapter;

    @Override
    protected void onCreated(@Nullable Bundle savedInstanceState) {
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
        return "评估报告";
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.activity_report1;
    }

}