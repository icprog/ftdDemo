package com.william.ftdui.widget.adapter.decoration;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int space = 10;

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        GridLayoutManager glm = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = glm.getSpanCount();
        int orientation = glm.getOrientation();
        GridLayoutManager.SpanSizeLookup lookup = glm.getSpanSizeLookup();

        int row,colum;
        if (orientation == OrientationHelper.VERTICAL){
//            row = lookup.getSpanGroupIndex(position, spanCount);
        } else {

        }

        float index = (float) parent.indexOfChild(view);
        float r = index / (float) spanCount;
        outRect.set(space, space, outRect.right - space, outRect.bottom - space);
    }
}