package com.william.ftd_core.param;

import com.william.ftd_core.Util;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.User;

import io.reactivex.annotations.NonNull;

public class GetQuestionParam {
    private int userType = 1;
    private String userCode;
    private String userId;
    private String equCode = ServiceApi.MAC;
    private String schemeId;
    private int checkType = 0;
    private String deviceType = "MOBILE";
    private String deviceCode = "MOBILE";
    private String space = "FACE_TONGUE_LK";
    private String constitutionSpace = "PD";

    public GetQuestionParam(@NonNull User user, String schemeId) {
        this.userId = user.getPhrId();
        this.userCode = user.getUserName();
        this.schemeId = schemeId;
    }

    public int getUserType() {
        return userType;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getEquCode() {
        return equCode;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public int getCheckType() {
        return checkType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getSpace() {
        return space;
    }

    public String getConstitutionSpace() {
        return constitutionSpace;
    }

    public void setConstitutionSpace(String constitutionSpace) {
        this.constitutionSpace = constitutionSpace;
    }
}
