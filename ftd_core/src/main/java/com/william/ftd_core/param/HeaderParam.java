package com.william.ftd_core.param;

public class HeaderParam {
    public String appKey;
    public String appId;
    public String appCode;
    public String companyCode;
    public String appSecret;
    public String companyId;
    public String companyPid;
    public String phrAppKey;
    public String phrAppSecret;
    public String lkToken = "";

    public HeaderParam() {
    }

    public HeaderParam(String appKey, String appId, String appCode, String companyCode, String appSecret, String companyId, String companyPid, String phrAppKey, String phrAppSecret, String lkToken) {
        this.appKey = appKey;
        this.appId = appId;
        this.appCode = appCode;
        this.companyCode = companyCode;
        this.appSecret = appSecret;
        this.companyId = companyId;
        this.companyPid = companyPid;
        this.phrAppKey = phrAppKey;
        this.phrAppSecret = phrAppSecret;
        this.lkToken = lkToken;
    }
}
