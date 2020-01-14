package com.lk.ftdui.activity.config;

import com.lk.ftdui.R;

public enum ErrorDisplay {
    ERROR(R.drawable.error,"请求失败，请检查网络连接！"),
    EMPTY(R.drawable.empty,"暂无来康师，请耐心等待哦～");
    private int errorImg;
    private String content;

    public int getErrorImg() {
        return errorImg;
    }

    public void setErrorImg(int errorImg) {
        this.errorImg = errorImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    ErrorDisplay(int errorImg, String content) {
        this.errorImg = errorImg;
        this.content = content;
    }
}
