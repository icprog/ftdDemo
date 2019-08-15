package com.william.ftd_core.entity;

import com.google.gson.annotations.SerializedName;

public class QuestionBean {

    /**
     * code : Q7
     * content : ★易乏力、气短、出汗，活动后尤甚
     */

    private String code;
    private String content;


    private transient boolean checked;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
