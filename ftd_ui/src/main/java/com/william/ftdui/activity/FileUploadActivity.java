package com.william.ftdui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.william.ftd_base.constant.Constant;
import com.william.ftd_base.constant.Step;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdMicroTipCallback;
import com.william.ftd_core.callback.FtdPicUploadCallback;
import com.william.ftd_core.entity.Conclusion;
import com.william.ftd_core.entity.FtdResponse;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.R;
import com.william.zhibiaoview.StepView;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class FileUploadActivity extends BaseActivity {

    private Button btnSubmit;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivRefresh;

    private TextView tvFaceUploadResult;
    private TextView tvTongueTopUploadResult;

    private NestedScrollView nsv;

    private StepView stepView;

    private ProgressBar pbSub;


    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnSubmit = findViewById(R.id.btn_submit);

        ivRefresh = findViewById(R.id.iv_refresh);
        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMicroTip();
            }
        });
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);

        tvFaceUploadResult = findViewById(R.id.tv_result_face);
        tvTongueTopUploadResult = findViewById(R.id.tv_result_tongue_top);

        nsv = findViewById(R.id.nsv);

        stepView = findViewById(R.id.step);

        File[] files = new File[3];
        files[0] = loadImg(Step.stepMap.get(Constant.STEP_FACE));
        files[1] = loadImg(Step.stepMap.get(Constant.STEP_TONGUE_TOP));
        files[2] = loadImg(Step.stepMap.get(Constant.STEP_TONGUE_BOTTOM));


        pbSub = findViewById(R.id.pb_sub);

        upload(files);

        getMicroTip();
    }

    @Override
    protected String setTitle() {
        return "面舌分析";
    }

    @Override
    public int setContentViewResId() {
        return R.layout.activity_file_upload;
    }

    private File loadImg(Step step) {

        if (step == null) {
            return null;
        }
        File file = new File(step.getPhotoPath());
        String stepID = step.getStepId();
        int ivResID = 0;
        if (stepID.equals(Constant.STEP_FACE)) {
            ivResID = R.id.iv_face;
        } else if (stepID.equals(Constant.STEP_TONGUE_TOP)) {
            ivResID = R.id.iv_tongue_top;
        }
        if (ivResID != 0) {
            ImageView iv = findViewById(ivResID);
            Glide.with(iv).load(file).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        }
        return file;
    }

    /**
     * 上传图片去分析
     */
    private void upload(File[] fileCollection) {
        Disposable fileUploadDisposable = FtdClient.getInstance().picUpload(fileCollection, new FtdPicUploadCallback() {
            @Override
            public void onSuccess(Conclusion result) {
                dismissProgress();
                FtdResponse<UploadResult> faceUploadResult = result.getFaceResult();
                FtdResponse<UploadResult> tongueUploadResult = result.getTongueTopResult();

                boolean faceSuccess = changeTvDescState(tvFaceUploadResult, faceUploadResult);
                boolean tongueTopSuccess = changeTvDescState(tvTongueTopUploadResult, tongueUploadResult);

                changeBtnSubmitState(faceSuccess, tongueTopSuccess);
            }

            @Override
            public void onError(FtdException e) {
                dismissProgress();
                showToast(e.getMsg());
            }
        });
        addDisposable(fileUploadDisposable);
    }


    private Boolean changeTvDescState(TextView tv, FtdResponse<UploadResult> response) {
        boolean b = response.getCode() == 1000;
        int drawableRes;
        String content;
        if (b) {
            drawableRes = R.drawable.result_correct;
            content = "分析成功";
        } else {
            drawableRes = R.drawable.result_wrong;
            content = response.getMsg();
        }
        tv.setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0);
        tv.setText(content);
        return b;
    }

    private void changeBtnSubmitState(final boolean face, final boolean tongueTop) {
        final boolean b = face && tongueTop;
        String content;
        if (b) {
            content = "去问诊";
            stepView.setSelectedIndex(3);
        } else {
            content = "重新拍摄";
        }
        btnSubmit.setText(content);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b) {
                    QuestionListActivity.start(FileUploadActivity.this);
                } else {
                    ArrayList<String> stepList = new ArrayList<>();
                    if (!face) {
                        stepList.add(Constant.STEP_FACE);
                    }
                    if (!tongueTop) {
                        stepList.add(Constant.STEP_TONGUE_TOP);
                    }
                    FtdActivity.getPicFromCamera(FileUploadActivity.this, stepList.toArray(new String[stepList.size()]));
                }
                finish();
            }
        });
        btnSubmit.setEnabled(true);
    }

    /**
     * 获取健康微语
     */
    private void getMicroTip() {
        pbSub.setVisibility(View.VISIBLE);
        nsv.setScrollY(0);
        Disposable tipDisposable = FtdClient.getInstance().getMicroTip(new FtdMicroTipCallback() {
            @Override
            public void onSuccess(MicroTipBean bean) {
                tvTitle.setText(bean.getName());
                tvContent.setText(bean.getAnalysis());
                pbSub.setVisibility(View.GONE);
            }

            @Override
            public void onError(FtdException e) {
                pbSub.setVisibility(View.GONE);
                tvContent.setText(e.getMsg());
            }
        });
        addDisposable(tipDisposable);
    }
}
