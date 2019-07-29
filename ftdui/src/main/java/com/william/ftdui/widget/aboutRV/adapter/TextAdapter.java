package com.william.ftdui.widget.aboutRV.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.william.ftdui.R;

import java.util.ArrayList;
import java.util.Collection;

public class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> list = new ArrayList<>(10);

    public void update(Collection list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text,viewGroup,false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((TextView)viewHolder.itemView).setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
