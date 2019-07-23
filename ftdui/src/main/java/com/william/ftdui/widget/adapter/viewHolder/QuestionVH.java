package com.william.ftdui.widget.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.william.ftd_core.entity.QuestionBean;
import com.william.ftdui.constant.Constant;
import com.william.ftdui.widget.adapter.QuestionAdapter;

public class QuestionVH extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

    CheckBox cb;
    //    QuestionAdapter.OnItemCheckedChangeListener listener;
    QuestionBean bean;
    private int trace;

    //    public QuestionVH(@NonNull View itemView, @Constant.Trace int trace, QuestionAdapter.OnItemCheckedChangeListener listener) {
//        super(itemView);
//        this.trace = trace;
//        cb = (CheckBox) itemView;
//        this.listener = listener;
//        cb.setOnCheckedChangeListener(this);
//    }
    public QuestionVH(@NonNull View itemView, @Constant.Trace int trace) {
        super(itemView);
        this.trace = trace;
        cb = (CheckBox) itemView;
//        cb.setOnCheckedChangeListener(this);
    }

    public void bind(final QuestionBean bean,final QuestionAdapter.OnItemCheckedChangeListener listener) {
        this.bean = bean;
        cb.setText(bean.getContent());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onItemCheckedChanged(trace, bean, isChecked);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (this.listener != null) {
//            listener.onItemCheckedChanged(this.trace, this.bean, isChecked);
//        }
    }
}
