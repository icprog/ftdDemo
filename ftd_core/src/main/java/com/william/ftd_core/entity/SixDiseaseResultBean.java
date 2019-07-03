package com.william.ftd_core.entity;

import java.util.ArrayList;

public class SixDiseaseResultBean {
    private double yinScore;
    private double yangScore;
    private double totalScore;
    private ArrayList<SixDiseaseListBean> sixDiseaseList;

    public double getYinScore() {
        return yinScore;
    }

    public void setYinScore(double yinScore) {
        this.yinScore = yinScore;
    }

    public double getYangScore() {
        return yangScore;
    }

    public void setYangScore(double yangScore) {
        this.yangScore = yangScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public ArrayList<SixDiseaseListBean> getSixDiseaseList() {
        return sixDiseaseList;
    }

    public void setSixDiseaseList(ArrayList<SixDiseaseListBean> sixDiseaseList) {
        this.sixDiseaseList = sixDiseaseList;
    }
}
