package com.william.ftd_core.entity;

public class DiseaseListBean {
    /**
     * score : 85
     * medical : CHINESE
     * code : ZZGV10
     * arterialScore : 0
     * name : 肝气郁结证
     * rule : GQYJZ01
     */

    private double score;
    private String medical;
    private String code;
    private double arterialScore;
    private String name;
    private String rule;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getArterialScore() {
        return arterialScore;
    }

    public void setArterialScore(double arterialScore) {
        this.arterialScore = arterialScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
