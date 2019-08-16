package com.william.ftd_core.param;


public class GetLastReportParam {

    private String appKey = "0f8d616cc71dc3e8";
    private String secret = "f68f0b290f8d616cc71dc3e85ddd02c5";
    private String checkTypeCode = "FACE_TONGUE_LK";
    private String constitutionSpace = "PD";

    public GetLastReportParam(String appKey, String secret) {
        this.appKey = appKey;
        this.secret = secret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public String getConstitutionSpace() {
        return constitutionSpace;
    }
}
