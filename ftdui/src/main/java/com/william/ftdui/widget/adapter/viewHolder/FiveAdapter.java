package com.william.ftdui.widget.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.william.ftd_core.FtdClient;
import com.william.ftdui.BuildConfig;
import com.william.ftdui.R;

import java.util.ArrayList;


public class FiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Five> array = new ArrayList<>();


    private OnWuYangSelectListener listener;

    public FiveAdapter(OnWuYangSelectListener listener) {
        this.listener = listener;
        array.add(new Five("食养", R.drawable.five_food, "foodRaiseTemp"));
        array.add(new Five("术养", R.drawable.five_massage, "kungfuRaiseTemp"));
        array.add(new Five("动养", R.drawable.five_sport, "actionRaiseTemp"));
        array.add(new Five("心养", R.drawable.five_mood, "heartRaiseTemp"));
        array.add(new Five("居养", R.drawable.five_live, "liveRaiseTemp"));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View iv = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_five, viewGroup, false);
        return new RecyclerView.ViewHolder(iv) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        final String path = array.get(i).urlPath;
        ImageView iv = (ImageView) viewHolder.itemView;
        iv.setImageResource(array.get(i).drawableRes);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onWuYangSelect(array.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public interface OnWuYangSelectListener {
        void onWuYangSelect(Five fiveBean);
    }

    public static class Five {
        private String reg = "%s%s?reportCode=%s&nextPageId=0&appkey=%s";
        private String title;
        private int drawableRes;
        private String urlPath;

        public Five(String title, int drawableRes, String urlPath) {
            this.title = title;
            this.drawableRes = drawableRes;
            this.urlPath = urlPath;
        }

        public String getTitle() {
            return title;
        }

        public int getDrawableRes() {
            return drawableRes;
        }

        public String getUrl(String diseaseId, String appKey) {
            return String.format(reg, BuildConfig.BRAIN_WEB, urlPath, diseaseId, appKey);
        }
    }
}
