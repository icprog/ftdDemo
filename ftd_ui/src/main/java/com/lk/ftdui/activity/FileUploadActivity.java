package com.lk.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lk.ftd_base.constant.Step;
import com.lk.ftd_core.TaskManager;
import com.lk.ftd_core.call.FtdMicroTipCallback;
import com.lk.ftd_core.call.FtdPicUploadCallback;
import com.lk.ftd_core.constant.Constant1;
import com.lk.ftd_core.entity.MicroTipBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftd_core.param.DiagnoseParam;
import com.lk.ftdui.R;
import com.william.zhibiaoview.StepView;
import java.io.File;
import java.util.ArrayList;

public class FileUploadActivity extends BaseActivity implements FtdPicUploadCallback, FtdMicroTipCallback {

    private Button btnSubmit;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivRefresh;

    private NestedScrollView nsv;

    private StepView stepView;

    private ProgressBar pbSub;

    private static SparseArray<DiagnoseParam> paramMap = new SparseArray<>(3);


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

        nsv = findViewById(R.id.nsv);

        stepView = findViewById(R.id.step);

        pbSub = findViewById(R.id.pb_sub);

        upload();
        getMicroTip();
    }

    private void upload() {
        int size = Constant1.TYPES.size();
        SparseArray<DiagnoseParam> diagnoseParams = new SparseArray<>();
        for (int i = 0; i < size; i++) {
            File file = loadImg(Step.stepMap.get(Constant1.TYPES.keyAt(i)));
            DiagnoseParam param = new DiagnoseParam(file, Constant1.TYPES.valueAt(i));
            diagnoseParams.put(Constant1.TYPES.keyAt(i), param);
        }
        TaskManager.instance.picUpload(diagnoseParams, this);
    }


    private synchronized void setResult(final DiagnoseParam param) {
        paramMap.put(param.getDiagnoseTag(), param);
        setContent(param);
        changeBtnSubmitState();
    }

    /**
     * 根据结果设置描述和图标
     *
     * @param param
     */
    private void setContent(final DiagnoseParam param) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int id;
                switch (param.getDiagnoseTag()) {
                    case Constant1.FACE:
                        id = R.id.tv_result_face;
                        break;
                    case Constant1.TONGUE_TOP:
                        id = R.id.tv_result_tongue_top;
                        break;
                    default:
                        return;
                }
                TextView tv = findViewById(id);
                FtdException e = param.getException();
                String content;
                int icon;
                if (e == null) {
                    content = "分析成功";
                    icon = R.drawable.result_correct;
                } else {
                    content = e.getMsg();
                    icon = R.drawable.result_wrong;
                }
                tv.setText(content);
                tv.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
            }
        });
    }

    /**
     * 改变按钮状态
     */
    private void changeBtnSubmitState() {
        runOnUiThread(new Runnable() {
            private ArrayList<Integer> failedIdList = new ArrayList<>(paramMap.size());

            @Override
            public void run() {
                int size = paramMap.size();
                failedIdList.clear();
                final ArrayList<DiagnoseParam> suceedParamList = new ArrayList<>();
                DiagnoseParam param;
                for (int i = 0; i < size; i++) {
                    param = paramMap.valueAt(i);
                    if (param.getException() != null) {
                        failedIdList.add(param.getDiagnoseTag());
                    } else if (param.getResult() != null) {
                        suceedParamList.add(param);
                    }
                }
                String content = "";
                View.OnClickListener listener = null;
                if (failedIdList.size() > 0) {
                    content = "重新拍摄";
                    listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            finish();
                            onclick(false);
                        }
                    };
                } else if (suceedParamList.size() == size) {
                    content = "去问诊";
                    stepView.setSelectedIndex(3);
                    listener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onclick(true);
                        }
                    };
                }
                btnSubmit.setText(content);
                btnSubmit.setOnClickListener(listener);
                btnSubmit.setEnabled(true);
            }

            private void onclick(boolean goToNext) {
                if (goToNext) {
//                    QuestionListActivity.start(FileUploadActivity.this);
                    Intent intent = getIntent();
                    intent.setClass(FileUploadActivity.this,QuestionListActivity.class);
                    startActivity(intent);
                } else {
                    FtdActivity.start(FileUploadActivity.this, failedIdList);
                }
                finish();
            }
        });
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
        int stepID = step.getTypeId();
        int ivResID = 0;
        if (stepID == Constant1.FACE) {
            ivResID = R.id.iv_face;
        } else if (stepID == Constant1.TONGUE_TOP) {
            ivResID = R.id.iv_tongue_top;
        }
        if (ivResID != 0) {
            ImageView iv = findViewById(ivResID);
            Glide.with(iv).load(file).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        }
        return file;
    }


    @Override
    public void onSucceed(final DiagnoseParam diagnoseParam) {
        setResult(diagnoseParam);
    }


    @Override
    public void onFailed(final DiagnoseParam diagnoseParam) {
        setResult(diagnoseParam);
    }

    @Override
    public void onError(final FtdException e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pbSub.setVisibility(View.GONE);
                tvContent.setText(e.getMsg());
            }
        });
    }


    /**
     * 获取健康微语
     */
    private void getMicroTip() {
        pbSub.setVisibility(View.VISIBLE);
        nsv.setScrollY(0);
        TaskManager.instance.getMicroTip(this);
    }

    @Override
    public void onSuccess(final MicroTipBean bean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTitle.setText(bean.getName());
                tvContent.setText(bean.getAnalysis());
                pbSub.setVisibility(View.GONE);
            }
        });
    }
}
