package com.lk.ftdui.widget.aboutRV.viewHolder.doctorDetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lk.ftd_core.entity.DoctorDetailBean;
import com.lk.ftdui.R;

import java.util.ArrayList;

/**
 * 医生详情「荣耀时刻」一栏
 * <p>
 * R.layout.item_doctor_detail_pictures
 */
public class DoctorDetailPicturesVH extends BaseVH {

    private RecyclerView rv;
    private Adapter adapter = new Adapter();

    public DoctorDetailPicturesVH(@NonNull View itemView) {
        super(itemView);

        rv = itemView.findViewById(R.id.rv);
        rv.setAdapter(adapter);
    }
    @Override
    public void bind(DoctorDetailBean bean) {
        adapter.setMemberDoctorPicPoList(bean.getMemberDoctorPicPoList());
    }

    private static class Adapter extends RecyclerView.Adapter<VH> {

        private ArrayList<DoctorDetailBean.DoctorPicture> memberDoctorPicPoList = new ArrayList<>();

        public void setMemberDoctorPicPoList(ArrayList<DoctorDetailBean.DoctorPicture> memberDoctorPicPoList) {
            this.memberDoctorPicPoList.clear();
            this.memberDoctorPicPoList.addAll(memberDoctorPicPoList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = View.inflate(viewGroup.getContext(), R.layout.item_iv, viewGroup);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH vh, int i) {
            vh.bind(memberDoctorPicPoList.get(i));
        }

        @Override
        public int getItemCount() {
            return memberDoctorPicPoList.size();
        }
    }

    private static class VH extends RecyclerView.ViewHolder {

        public VH(@NonNull View itemView) {
            super(itemView);
        }

        void bind(DoctorDetailBean.DoctorPicture pic) {
            Glide.with(itemView).load(pic.getPicUrl()).into((ImageView) itemView);
        }
    }
}
