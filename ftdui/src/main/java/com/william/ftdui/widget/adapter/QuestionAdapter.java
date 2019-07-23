package com.william.ftdui.widget.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.william.ftd_core.entity.CardInfoBean;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftdui.constant.Constant;
import com.william.ftdui.R;
import com.william.ftdui.widget.adapter.viewHolder.QuestionVH;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionVH> {

    private int trace;

    private OnItemCheckedChangeListener listener;
    private ArrayList<QuestionBean> questionList = new ArrayList<QuestionBean>();

    public QuestionAdapter(OnItemCheckedChangeListener listener) {
        this.listener = listener;
    }

//    public void addData(ArrayList<QuestionBean> questionList){
//        this.questionList.addAll(questionList);
//        notifyDataSetChanged();
//    }
//    TRACE_ZHI
    public void addData(CardInfoBean bean, @Constant.Trace int trace) {
        this.trace = trace;
        this.questionList.addAll(bean.getAskingItemList());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QuestionVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question,viewGroup,false);
        return new QuestionVH(view,this.trace);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionVH questionVH, int i) {
        questionVH.bind(questionList.get(i),this.listener);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public interface OnItemCheckedChangeListener{
        void onItemCheckedChanged(@Constant.Trace int trace, QuestionBean question,boolean isChecked);
    }
}
