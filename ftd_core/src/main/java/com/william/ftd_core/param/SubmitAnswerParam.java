package com.william.ftd_core.param;

import android.provider.SyncStateContract;

import com.william.ftd_core.BuildConfig;
import com.william.ftd_core.constant.ServiceApi;

public class SubmitAnswerParam {

    private int flag = 1;
    private String userId;
    private String space = "FACE_TONGUE_LK";
    private String deviceCode = "MOBILE";
    private String deviceType = "MOBILE";
    private String userCode;
    private String schemeId;
    private int checkType = 1;
    private String traceId;
    private String westernMedicineInfo;
    private String constitutionWesternMedicineInfo;
    private String equCode = ServiceApi.MAC;
    private String constitutionTraceId;
    private String constitutionSpace = "PD";

    public SubmitAnswerParam(String userId, String userCode, String schemeId, String traceId, String westernMedicineInfo, String constitutionWesternMedicineInfo, String constitutionTraceId) {
        this.userId = userId;
        this.userCode = userCode;
        this.schemeId = schemeId;
        this.traceId = traceId;
        this.westernMedicineInfo = westernMedicineInfo;
        this.constitutionWesternMedicineInfo = constitutionWesternMedicineInfo;
        this.constitutionTraceId = constitutionTraceId;
    }

    public int getFlag() {
        return flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpace() {
        return space;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public int getCheckType() {
        return checkType;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getWesternMedicineInfo() {
        return westernMedicineInfo;
    }

    public void setWesternMedicineInfo(String westernMedicineInfo) {
        this.westernMedicineInfo = westernMedicineInfo;
    }

    public String getConstitutionWesternMedicineInfo() {
        return constitutionWesternMedicineInfo;
    }

    public void setConstitutionWesternMedicineInfo(String constitutionWesternMedicineInfo) {
        this.constitutionWesternMedicineInfo = constitutionWesternMedicineInfo;
    }

    public String getEquCode() {
        return equCode;
    }

    public String getConstitutionTraceId() {
        return constitutionTraceId;
    }

    public void setConstitutionTraceId(String constitutionTraceId) {
        this.constitutionTraceId = constitutionTraceId;
    }

    public String getConstitutionSpace() {
        return constitutionSpace;
    }
}
