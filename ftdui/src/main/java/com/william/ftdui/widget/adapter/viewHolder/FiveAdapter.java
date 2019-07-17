package com.william.ftdui.widget.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.william.ftd_core.FtdClient;
import com.william.ftdui.BuildConfig;
import com.william.ftdui.R;



public class FiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String reg = "%s%s?reportCode=%s&nextPageId=0&appkey=%s";

    private String diseaseId;

    private SparseArray<String> array = new SparseArray<String>();


    private OnWuYangSelectListener listener;

    public FiveAdapter(String diseaseId, OnWuYangSelectListener listener) {
        this.diseaseId = diseaseId;
        this.listener = listener;
        array.append(R.drawable.five_food, "foodRaiseTemp");
        array.append(R.drawable.five_massage, "kungfuRaiseTemp");
        array.append(R.drawable.five_sport, "actionRaiseTemp");
        array.append(R.drawable.five_mood, "heartRaiseTemp");
        array.append(R.drawable.five_live, "liveRaiseTemp");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View iv = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_five,viewGroup,false);
        return new RecyclerView.ViewHolder(iv){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final String path = array.get(array.keyAt(i));
        ImageView iv = (ImageView) viewHolder.itemView;
        iv.setImageResource(array.keyAt(i));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
//                    String url = String.format(reg, BuildConfig.BRAIN_WEB, path, diseaseId, BuildConfig.BRAIN_APP_KEY);
                    String url = String.format(reg, BuildConfig.BRAIN_WEB, path, diseaseId, FtdClient.getInstance().getAppKey());
                    listener.onWuYangSelect(url);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public interface OnWuYangSelectListener {
        void onWuYangSelect(String url);
    }
}
