package com.lk.ftdui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.lk.ftd_base.constant.Constant;
import com.lk.ftd_core.task.FtdCore;
import com.lk.ftd_core.callback.FtdQuestionListCallback;
import com.lk.ftd_core.callback.FtdSubmitCallback;
import com.lk.ftd_core.entity.AskBean;
import com.lk.ftd_core.entity.CardInfoBean;
import com.lk.ftd_core.entity.QuestionBean;
import com.lk.ftd_core.exception.FtdException;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.adapter.QuestionAdapter;

import java.util.LinkedList;

public class QuestionListActivity extends BaseActivity implements
        QuestionAdapter.OnItemCheckedChangeListener {

    private RecyclerView rv1;
    private RecyclerView rv2;
    private QuestionAdapter adapter1 = new QuestionAdapter(this);
    private QuestionAdapter adapter2 = new QuestionAdapter(this);

    private Button btnSubmit;

    private LinkedList<QuestionBean> questionList1 = new LinkedList<>();
    private LinkedList<QuestionBean> questionList2 = new LinkedList<>();

    private String traceId1;
    private String traceId2;

    private FtdQuestionListCallback questionCallback = new FtdQuestionListCallback() {
        @Override
        public void onSuccess(final AskBean bean) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    hideProgress();
                    CardInfoBean bean1 = bean.getCardInfo();
                    CardInfoBean bean2 = bean.getConstitutionInfo();
                    traceId1 = bean1.getTraceId();
                    traceId2 = bean2.getTraceId();
                    adapter1.addData(bean1, Constant.TRACE_ZHENG);
                    adapter2.addData(bean2, Constant.TRACE_ZHI);
                    hideProgress();
                    btnSubmit.setEnabled(true);
//                }
//            });
        }

        @Override
        public void onError(final FtdException e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    hideProgress();
                    showToast(e.getMsg());
//                }
//            });
        }
    };
    private FtdSubmitCallback answerCallback = new FtdSubmitCallback() {
        @Override
        public void onSuccess(final AskBean bean) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    hideProgress();
//                    ReportActivity.start(QuestionListActivity.this, bean.getCardInfo().getSeqNo(), bean.getConstitutionInfo().getSeqNo());
                    Intent intent = getIntent();
                    intent.setClass(QuestionListActivity.this, ReportActivity.class);
                    intent.putExtra("cardSeqNo", bean.getCardInfo().getSeqNo());
                    intent.putExtra("constitutionSeqNo", bean.getConstitutionInfo().getSeqNo());
                    startActivity(intent);
                    finish();
//                }
//            });
        }

        @Override
        public void onError(final FtdException e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
                    hideProgress();
                    showToast(e.getMsg());
//                }
//            });
        }
    };

    public static void start(Context context) {
        Intent intent = new Intent(context, QuestionListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.btnSubmit = findViewById(R.id.btn_submit);
        this.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        this.rv1 = findViewById(R.id.rv1);
        this.rv1.setAdapter(adapter1);

        this.rv2 = findViewById(R.id.rv2);
        this.rv2.setAdapter(adapter2);

        addTask(FtdCore.instance.getQuestionList(true,questionCallback));
    }

    private void submit() {
        if (questionList1.size() > 4) {
            showToast("体质最多只能选择4项哦！");
            return;
        }
        if (questionList2.size() > 4) {
            showToast("体证最多只能选择4项哦！");
            return;
        }
        showProgress();
        addTask(FtdCore.instance.submitAnswer(true,questionList1, questionList2, traceId1, traceId2, answerCallback));
    }



    @Override
    protected String setTitle() {
        return "问诊";
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
}
