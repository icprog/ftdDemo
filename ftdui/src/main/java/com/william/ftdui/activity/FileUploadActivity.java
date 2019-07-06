package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdMicroTipCallback;
import com.william.ftd_core.callback.FtdPicUploadCallback;
import com.william.ftd_core.entity.MicroTipBean;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.constant.Constant;
import com.william.ftdui.R;

import java.io.File;

public class FileUploadActivity extends BaseActivity implements View.OnClickListener {

    private Button btnSubmit;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_upload);

        ImageView ivFace = findViewById(R.id.iv_face);
        ImageView ivTongueTop = findViewById(R.id.iv_tongue_top);
        ImageView ivTongueBottom = findViewById(R.id.iv_tongue_bottom);

        btnSubmit = findViewById(R.id.submit);
        btnSubmit.setOnClickListener(this);

        ivRefresh = findViewById(R.id.iv_refresh);
        ivRefresh.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);

        File FaceImg = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), Constant.steps.get(Constant.STEP_FACE).getFileName() + ".jpeg");
        File TongueTopImg = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), Constant.steps.get(Constant.STEP_TONGUE_TOP).getFileName() + ".jpeg");
        File TongueBottomImg = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), Constant.steps.get(Constant.STEP_TONGUE_BOTTOM).getFileName() + ".jpeg");

        loadImg(ivFace, FaceImg);
        loadImg(ivTongueTop, TongueTopImg);
        loadImg(ivTongueBottom, TongueBottomImg);



        getMicroTip();
    }

    private void loadImg(@NonNull ImageView iv, @NonNull File file) {
        Glide.with(iv).load(file).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_refresh) {
            getMicroTip();
        } else {
            Intent intent = getIntent();
            intent.setClass(this, QuestionListActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 图片在上传过程中关闭页面，容易引发内存泄露，我们提供了stopUpload()方法，可在onDestroy()中调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FtdClient.getInstance().stopUpload();
    }


    private void getMicroTip() {
        FtdClient.getInstance().getMicroTip(new FtdMicroTipCallback() {
            @Override
            public void onSuccess(MicroTipBean bean) {
                // TODO: 2019-07-06
                tvTitle.setText(bean.getName());
                tvContent.setText(bean.getAnalysis());
            }

            @Override
            public void onError(FtdException e) {
                showToast(e.getMsg());
            }
        });
    }
}
