package com.william.ftd_core.entity;

import java.util.List;

public  class DataBean {
    /**
     * score : 6
     * quotaDesc : 面色白：主虚证，寒证，失血等。
     * medical : 1
     * quotaName : 面色
     * normalScore : 10
     * values : [{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"面色苍白","value":"11305"},{"score":3,"text":"面色赤、暗红","value":"11403"},{"score":4,"text":"面色黑","value":"1190"},{"score":6,"text":"面色赤","value":"1140"},{"score":6,"text":"面色白","value":"1130"},{"score":6,"text":"面色黄","value":"1120"},{"score":10,"text":"面色红黄隐隐、明润含蓄","value":"11205"}]
     * index : 1
     * quotaValueName : 面色白
     * quotaCode : XYZBZY001004
     * quotaValue : 1130
     */

    private double score;
    private String quotaDesc;
    private double medical;
    private String quotaName;
    private double normalScore;
    private int index;
    private String quotaValueName;
    private String quotaCode;
    private String quotaValue;
    private List<ValuesBean> values;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getQuotaDesc() {
        return quotaDesc;
    }

    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public String getQuotaName() {
        return quotaName;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }

    public double getNormalScore() {
        return normalScore;
    }

    public void setNormalScore(double normalScore) {
        this.normalScore = normalScore;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getQuotaValueName() {
        return quotaValueName;
    }

    public void setQuotaValueName(String quotaValueName) {
        this.quotaValueName = quotaValueName;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public String getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(String quotaValue) {
        this.quotaValue = quotaValue;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }
}