package com.william.ftd_core.entity;

/**
 * 总评分
 */
public class EvaluationScoreBean {
    /**
     * kScore : 91.3
     * advise : 建议您定期复测
     * scoreLevel : GREEN
     * name : 察颜观舌
     * space : FACE_TONGUE_LK
     */

    private double kScore;
    private String advise;
    private String scoreLevel;
    private String name;
    private String space;

    public double getKScore() {
        return kScore;
    }

    public void setKScore(double kScore) {
        this.kScore = kScore;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(String scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}
