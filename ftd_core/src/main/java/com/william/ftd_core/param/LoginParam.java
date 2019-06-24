package com.william.ftd_core.param;

public class LoginParam {

    private String userTel;
    private String companyCode;
    private String AppId;
    private String thirdToken;

    public LoginParam(String userTel, String companyCode, String appId, String thirdToken) {
        this.userTel = userTel;
        this.companyCode = companyCode;
        AppId = appId;
        this.thirdToken = thirdToken;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getThirdToken() {
        return thirdToken;
    }

    public void setThirdToken(String thirdToken) {
        this.thirdToken = thirdToken;
    }
}
