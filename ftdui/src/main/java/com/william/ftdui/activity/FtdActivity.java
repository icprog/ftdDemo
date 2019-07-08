package com.william.ftdui.activity;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdLoginCallback;
import com.william.ftd_core.exception.FtdException;
import com.william.ftd_core.param.InitParam;
import com.william.ftdui.BuildConfig;
import com.william.ftdui.R;
import com.william.ftdui.fragment.CameraFragment;

import java.io.File;

public class FtdActivity extends BaseActivity
        implements CameraFragment.OnFragmentInteractionListener {

//    private StepView mStepView;

    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;
    public static final int ASK = 3;

    @IntDef(value = {FACE, TONGUE_TOP, TONGUE_BOTTOM, ASK})
    @interface Step {
    }

    private CameraFragment fragment;

    private FtdClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ftd);

//        String appId = "1561015389";
//        String appCode = "9000037";
//        String appKey = BuildConfig.BRAIN_APP_KEY;
//        String appSecret = BuildConfig.BRAIN_APP_SECRET;
//        String companyId = "1";
//        String companyPid = "1";
//
//
//        //盼盼版
////        String companyCode = "LK6663b8aa1ef246d0";
////        String phrAppKey = "0f8d616cc71dc3e8";
////        String phrAppSecret = "f68f0b290f8d616cc71dc3e85ddd02c5";
//        //冰冰版
//        String companyCode = "LKde25dbbdacb442a3";
//        String phrAppKey = "5c93d6f90b635ebd ";
//        String phrAppSecret = "ddcba9615c93d6f90b635ebdfd59ce48";
//
//        InitParam param = new InitParam(
//                appId,
//                appCode,
//                appKey,
//                appSecret,
//                companyId,
//                companyPid,
//                companyCode,
//                phrAppKey,
//                phrAppSecret
//        );

        mClient = FtdClient.getInstance();
        mClient.init(this);

//        String mobile = "17326971126";
        String mobile = "17736703905";

        mClient.login(mobile, new FtdLoginCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(FtdException e) {
                showToast(e.getMsg());
            }
        });
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
//        mClient.pictureUpload(file,requestId);
    }
}
