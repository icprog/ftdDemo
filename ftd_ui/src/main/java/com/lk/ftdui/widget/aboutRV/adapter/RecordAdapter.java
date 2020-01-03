package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.entity.RecordListBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.viewHolder.RecordVH;

public class RecordAdapter extends ListAdapter<RecordListBean.RecordBean, RecordVH> {

    private OnSelectedListener listener;

    public RecordAdapter(OnSelectedListener listener) {
        super(new DiffUtil.ItemCallback<RecordListBean.RecordBean>() {

            @Override
            public boolean areItemsTheSame(@NonNull RecordListBean.RecordBean b1, @NonNull RecordListBean.RecordBean b2) {
                return TextUtils.equals(b1.getSeqNo() , b2.getSeqNo());
            }

            @Override
            public boolean areContentsTheSame(@NonNull RecordListBean.RecordBean b1, @NonNull RecordListBean.RecordBean b2) {
                return true;
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecordVH onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record, viewGroup, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemSelected(getItem(i));
            }
        });
        return new RecordVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordVH vh, int i) {
        vh.bind(getItem(i));
    }

    public interface OnSelectedListener {
        /**
         * 条目被选中时回调
         */
        void onItemSelected(RecordListBean.RecordBean item);
    }
}
