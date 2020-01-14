package com.lk.ftdui.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewReplaceUtil {

    private View targetView;
    private Context context;
    private ViewGroup parent;
    private View emptyView;
    private View errorView;
    private ViewGroup.MarginLayoutParams lp;
    private View currentView;

    public void init(View targetView, int emptyLayoutResourceID, int errorLayoutResourceID) {
        this.targetView = targetView;
        this.currentView = targetView;
        context = targetView.getContext();
        parent = (ViewGroup) targetView.getParent();
        lp = (ViewGroup.MarginLayoutParams) targetView.getLayoutParams();

        emptyView = LayoutInflater.from(context).inflate(emptyLayoutResourceID, null, false);
        emptyView.setLayoutParams(lp);

        errorView = LayoutInflater.from(context).inflate(errorLayoutResourceID, null, false);
        errorView.setLayoutParams(lp);
    }

    public void setErrorView(View emptyView,View errorView) {
        this.emptyView = emptyView;
        this.errorView = errorView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
        this.currentView = targetView;
        context = targetView.getContext();
        parent = (ViewGroup) targetView.getParent();
        lp = (ViewGroup.MarginLayoutParams) targetView.getLayoutParams();
    }

    public void showEmpty() {
        replace(emptyView);
    }

    public void showError() {
        replace(errorView);
    }

    public void restore() {
        replace(targetView);
    }

    private void replace(View v) {
        int viewIndex = parent.indexOfChild(currentView);
        parent.removeView(currentView);
        parent.addView(v, viewIndex, lp);
        currentView = v;
    }
}
