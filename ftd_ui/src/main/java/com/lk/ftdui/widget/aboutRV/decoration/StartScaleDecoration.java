package com.lk.ftdui.widget.aboutRV.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lk.ftdui.R;

public class StartScaleDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 1, 0, 1);
    }

    private Paint paint = new Paint();

    public StartScaleDecoration() {
        paint.setColor(Color.parseColor("#ededed"));
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View itemView = parent.getChildAt(i);
            int y = itemView.getBottom();
            c.drawLine(20,y,itemView.getRight(),y,paint);
        }
    }
}
