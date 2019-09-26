package com.william.ftdui.widget.aboutRV.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.william.ftd_base.constant.Constant;
import com.william.ftd_core.entity.QuestionBean;
import com.william.ftdui.R;
import com.william.ftdui.widget.aboutRV.adapter.QuestionAdapter;

public class QuestionVH extends RecyclerView.ViewHolder {

    public CheckBox cb;
    public TextView tv;

    QuestionBean bean;
    private int trace;

    public QuestionVH(@NonNull View itemView, @Constant.Trace int trace) {
        super(itemView);
        this.trace = trace;
        cb =  itemView.findViewById(R.id.cb);
        tv = itemView.findViewById(R.id.tv);
    }

    public void bind(final QuestionBean bean,final QuestionAdapter.OnItemCheckedChangeListener listener) {
        this.bean = bean;
        tv.setText(bean.getContent());
        cb.setChecked(bean.isChecked());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onItemCheckedChanged(trace, bean, isChecked);
            }
        });
    }

}
