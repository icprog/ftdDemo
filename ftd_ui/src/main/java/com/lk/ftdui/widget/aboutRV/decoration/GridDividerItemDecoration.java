package com.lk.ftdui.widget.aboutRV.decoration;


import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 10;
    private int spanCount;
    private int orientation;

    public GridDividerItemDecoration(GridLayoutManager glm) {
        spanCount = glm.getSpanCount();
        orientation = glm.getOrientation();
    }

    private int width;
    private int childWidth;

    int left;
    int top;
    int right;
    int bottom;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        width = parent.getWidth();
        childWidth = width - (space * spanCount + 1) / space;

//        if (parent.getChildLayoutPosition(view) % 2 == 0) {

            left = space;
            top = outRect.top + space;
            right = left + childWidth;
            bottom = outRect.bottom + space;

            outRect.set(left, top, right, bottom);

        }
    }