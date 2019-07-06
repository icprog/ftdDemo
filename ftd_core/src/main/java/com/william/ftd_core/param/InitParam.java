package com.william.ftd_core.param;

public class InitParam {

    private String appId;
    private String appCode;
    private String appKey;
    private String appSecret;
    private String companyId;
    private String companyPid;
    private String companyCode;
    private String phrAppKey;
    private String phrAppSecret;

    public InitParam() {
    }

    public InitParam(String appId, String appCode, String appKey, String appSecret, String companyId, String companyPid, String companyCode, String phrAppKey, String phrAppSecret) {
        this.appId = appId;
        this.appCode = appCode;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.companyId = companyId;
        this.companyPid = companyPid;
        this.companyCode = companyCode;
        this.phrAppKey = phrAppKey;
        this.phrAppSecret = phrAppSecret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyPid() {
        return companyPid;
    }

    public void setCompanyPid(String companyPid) {
        this.companyPid = companyPid;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPhrAppKey() {
        return phrAppKey;
    }

    public void setPhrAppKey(String phrAppKey) {
        this.phrAppKey = phrAppKey;
    }

    public String getPhrAppSecret() {
        return phrAppSecret;
    }

    public void setPhrAppSecret(String phrAppSecret) {
        this.phrAppSecret = phrAppSecret;
    }
}
