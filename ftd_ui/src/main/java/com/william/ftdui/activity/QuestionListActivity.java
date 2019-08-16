package com.william.ftdui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.william.ftd_base.constant.Constant;
import com.william.ftd_core.FtdClient;
import com.william.ftd_core.callback.FtdQuestionListCallback;
import com.william.ftd_core.callback.FtdSubmitCallback;
import com.william.ftd_core.entity.AskBean;
import com.william.ftd_core.entity.CardInfoBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftd_core.exception.FtdException;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.adapter.QuestionAdapter;
import java.util.LinkedList;
import io.reactivex.disposables.Disposable;

public class QuestionListActivity extends BaseActivity implements
        QuestionAdapter.OnItemCheckedChangeListener,
        FtdSubmitCallback {

    private RecyclerView rv1;
    private RecyclerView rv2;
    private QuestionAdapter adapter1 = new QuestionAdapter(this);
    private QuestionAdapter adapter2 = new QuestionAdapter(this);

    private LinkedList<QuestionBean> questionList1 = new LinkedList<>();
    private LinkedList<QuestionBean> questionList2 = new LinkedList<>();

    private String traceId1;
    private String traceId2;

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.rv1 = findViewById(R.id.rv1);
        this.rv1.setAdapter(adapter1);

        this.rv2 = findViewById(R.id.rv2);
        this.rv2.setAdapter(adapter2);

        Disposable disposable = FtdClient.getInstance().getQuestion(new FtdQuestionListCallback() {
            @Override
            public void onSuccess(AskBean bean) {
                CardInfoBean bean1 = bean.getCardInfo();
                CardInfoBean bean2 = bean.getConstitutionInfo();
                traceId1 = bean1.getTraceId();
                traceId2 = bean2.getTraceId();
                adapter1.addData(bean1, Constant.TRACE_ZHI);
                adapter2.addData(bean2, Constant.TRACE_ZHENG);
                hideProgress();
            }

            @Override
            public void onError(FtdException e) {
                hideProgress();
                showToast(e.getMsg());
            }
        });
        addDisposable(disposable);
    }

    @Override
    protected void setEndTv(TextView tv) {
        tv.setText("提交");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionList1.size() > 4) {
                    showToast("体质最多只能选择4项哦！");
                    return;
                }
                if (questionList2.size() > 4) {
                    showToast("体证最多只能选择4项哦！");
                    return;
                }
                showProgress();
                Disposable disposable = FtdClient.getInstance().submitAnswer(questionList1, questionList2, traceId1, traceId2, QuestionListActivity.this);
                addDisposable(disposable);
            }
        });
    }

    @Override
    protected String setTitle() {
        return "评估问诊";
    }

    @Override
    public int setContentViewResId() {
        return R.layout.activity_question_list;
    }


    @Override
    public void onItemCheckedChanged(int trace, QuestionBean question, boolean isChecked) {
        if (isChecked) {
            if (trace == Constant.TRACE_ZHI) {
                questionList1.add(question);
            } else {
                questionList2.add(question);
            }
        } else {
            if (trace == Constant.TRACE_ZHI) {
                questionList1.remove(question);
            } else {
                questionList2.remove(question);
            }
        }
    }

    @Override
    public void onSuccess(AskBean bean) {
        hideProgress();
        Intent intent = new Intent(this, ReportActivity.class);
        long cardSeqNo = bean.getCardInfo().getSeqNo();
        long constitutionSeqNo = bean.getConstitutionInfo().getSeqNo();
        intent.putExtra("cardSeqNo", cardSeqNo);
        intent.putExtra("constitutionSeqNo",constitutionSeqNo);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(FtdException e) {
        hideProgress();
        showToast(e.getMsg());
    }
}
