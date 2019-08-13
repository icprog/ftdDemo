package com.william.ftdui.widget.aboutRV.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DotDecoration extends RecyclerView.ItemDecoration {

    private int paddingBottom = 20;
    private int paddingLeft = 50;
    private float radius = 12;

    private Paint dotPaint = new Paint();
    private Paint linePaint = new Paint();

    public DotDecoration() {
        dotPaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#dddddd"));
    }

    private int left;
    private int top;
    private int right;
    private int bottom;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (view instanceof LinearLayout) {
            View v = ((LinearLayout) view).getChildAt(0);
            v.setPadding(paddingLeft, v.getPaddingTop(), v.getPaddingRight(), v.getPaddingBottom());
            top = outRect.top + 20;
            bottom = outRect.bottom + 40;
        } else {
            left = outRect.left + paddingLeft;
            top = outRect.top + paddingBottom;
            right = outRect.right;
            bottom = outRect.bottom + paddingBottom;
        }

        outRect.set(left, top, right, bottom);
    }


    private int count;
    private View itemView;
    private View targetView;
    private float startX = paddingLeft / 2;
    private float startY;

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            itemView = parent.getChildAt(i);
            targetView = itemView;
            if (itemView instanceof LinearLayout) {
                targetView = ((ViewGroup) targetView).getChildAt(0);
            }
            startY = itemView.getTop() + ((float) targetView.getHeight()) / 2f;
            c.drawCircle(startX, startY, radius, dotPaint);
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        count = parent.getChildCount();
        for (int i = 0; i < count - 1; i++) {
            itemView = parent.getChildAt(i);
            if (itemView instanceof LinearLayout) {
                c.drawLine(0f, itemView.getBottom() + paddingBottom, itemView.getRight(), itemView.getBottom() + paddingBottom, linePaint);
            }
        }
    }
}
