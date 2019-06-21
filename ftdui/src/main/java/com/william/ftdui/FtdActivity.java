package com.william.ftdui;

import android.net.Uri;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shuhart.stepview.StepView;

public class FtdActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener,
        CameraFragment.OnFragmentInteractionListener {

    private StepView mStepView;
    private ViewPager mViewPager;
    private MainVPAdapter mAdapter;

    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;
    public static final int ASK = 3;

    @IntDef(value = {FACE,TONGUE_TOP,TONGUE_BOTTOM,ASK})
    @interface Step {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftd);

        mStepView = findViewById(R.id.step);
        mViewPager = findViewById(R.id.vp);

        mAdapter = new MainVPAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        mStepView.go(i,true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCaptrueComplete(int requestId) {
        switch (requestId){
            case FACE:
                mViewPager.setCurrentItem(TONGUE_TOP);
                break;
            case TONGUE_TOP:
                mViewPager.setCurrentItem(TONGUE_BOTTOM);
                break;
            case TONGUE_BOTTOM:
//                mViewPager.setCurrentItem(TONGUE_BOTTOM); todo 问诊
                break;
                default:
                    //todo 评估报告
        }

    }
}
