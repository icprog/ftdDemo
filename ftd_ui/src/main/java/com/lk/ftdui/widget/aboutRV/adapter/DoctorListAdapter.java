package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lk.ftd_core.entity.DoctorListBean;
import com.lk.ftdui.R;
import com.lk.ftdui.widget.aboutRV.viewHolder.ChooseDoctorVH;

public class DoctorListAdapter extends ListAdapter<DoctorListBean.DoctorBean, ChooseDoctorVH> {

    private OnSelectedListener listener;

    public DoctorListAdapter(OnSelectedListener listener) {
        super(new DiffUtil.ItemCallback<DoctorListBean.DoctorBean>() {

            @Override
            public boolean areItemsTheSame(@NonNull DoctorListBean.DoctorBean doctorBean, @NonNull DoctorListBean.DoctorBean t1) {
                return doctorBean.getId() == t1.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull DoctorListBean.DoctorBean doctorBean, @NonNull DoctorListBean.DoctorBean t1) {
                return true;
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChooseDoctorVH onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_doctor, viewGroup, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDoctorSelected(getItem(i));
            }
        });
        return new ChooseDoctorVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseDoctorVH vh, int i) {
        vh.bind(getItem(i),this.listener);
    }

    public interface OnSelectedListener {
        /**
         * 医生条目被选中时回调
         */
        void onDoctorSelected(DoctorListBean.DoctorBean item);

        /**
         * 医生条目中「诊断」按钮点击回调
         */
        void onDoctorChoosed(DoctorListBean.DoctorBean item);
    }
}
