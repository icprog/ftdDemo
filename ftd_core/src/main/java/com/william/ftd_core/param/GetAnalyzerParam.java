package com.william.ftd_core.param;

public class GetAnalyzerParam {
    private String diseaseIds;

    public GetAnalyzerParam(String diseaseIds) {
        this.diseaseIds = diseaseIds;
    }

    public String getDiseaseIds() {
        return diseaseIds;
    }

    public void setDiseaseIds(String diseaseIds) {
        this.diseaseIds = diseaseIds;
    }
}
