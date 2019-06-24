package com.william.ftdui;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.william.ftd_core.FtdClient;

import java.io.File;

public class FtdActivity1 extends AppCompatActivity
        implements ViewPager.OnPageChangeListener,
        CameraFragment.OnFragmentInteractionListener {

//    private StepView mStepView;

    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;
    public static final int ASK = 3;

    @IntDef(value = {FACE,TONGUE_TOP,TONGUE_BOTTOM,ASK})
    @interface Step {}

    private CameraFragment fragment;

    private FtdClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftd);

//        mStepView = findViewById(R.id.step);
//        fragment = findViewById(R.id.vp);
        String companyId = "LK6663b8aa1ef246d0";
        String appId = "1561015389";

         mClient = FtdClient.getInstance(companyId,appId);

        mClient.login("17736703905");
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
//        mStepView.go(i,true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCaptrueComplete(int requestId, File file) {
//        switch (requestId){
//            case FACE:
//                mViewPager.setCurrentItem(TONGUE_TOP);
//                break;
//            case TONGUE_TOP:
//                mViewPager.setCurrentItem(TONGUE_BOTTOM);
//                break;
//            case TONGUE_BOTTOM:
////                mViewPager.setCurrentItem(TONGUE_BOTTOM); todo 问诊
//                break;
//                default:
//                    //todo 评估报告
//        }
        mClient.picUpload(file,requestId);
    }
}
