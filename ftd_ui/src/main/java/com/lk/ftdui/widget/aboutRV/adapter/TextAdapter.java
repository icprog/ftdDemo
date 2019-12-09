package com.lk.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lk.ftd_core.entity.DataBean;
import com.lk.ftd_core.entity.QuotaInfoListBean;
import com.lk.ftdui.R;

import java.util.ArrayList;
import java.util.Collection;

public class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<QuotaInfoListBean> list = new ArrayList<>(10);

    public void update(Collection list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int index) {
        ArrayList<Scope> scopeList = new ArrayList<>(10);
        ArrayList<DataBean> beanList = (ArrayList) list.get(index).getData();
        int size = beanList.size();
        StringBuffer sb = new StringBuffer();
//        quotaNormal =1 不正常 |  =2 正常
        for (int i = 0; i < size; i++) {
            String quotaValueName = beanList.get(i).getQuotaValueName();
            if (beanList.get(i).getQuotaNormal() == 1) {
                Scope scope = new Scope(sb.length(), quotaValueName.length());
                scopeList.add(scope);
            }
            sb.append(quotaValueName);
            if (i < size - 1) {
                sb.append("，");
            }
        }
        SpannableString ss = new SpannableString(sb.toString());
        ForegroundColorSpan fcs;
        Scope scope;
        for (int i = 0; i < scopeList.size(); i++) {
            scope = scopeList.get(i);
            fcs = new ForegroundColorSpan(viewHolder.itemView.getContext().getResources().getColor(R.color.colorWrong));
            ss.setSpan(fcs, scope.start, scope.end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        ((TextView) viewHolder.itemView).setText(ss);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class Scope {
        int start;
        int end;

        public Scope(int start, int length) {
            this.start = start;
            this.end = start + length;
        }
    }
}
