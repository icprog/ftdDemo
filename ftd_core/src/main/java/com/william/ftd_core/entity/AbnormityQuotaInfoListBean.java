package com.william.ftd_core.entity;


import java.util.ArrayList;

public  class AbnormityQuotaInfoListBean {
    /**
     * optionalValueList : [{"text":"NA","value":"-1","normalValue":false},{"text":"无光泽","value":"2810","normalValue":false},{"text":"有光泽","value":"2830","normalValue":true},{"text":"少量光泽","value":"2820","normalValue":false}]
     * medical : CHINESE
     * quotaName : 光泽
     * maxValue : 0
     * quotaCauseList : [{"expression":"x==2820","cause":"提示脏腑精气不足。"}]
     * icon : null
     * quotaCatCode : XYZBZY001
     * quotaValue : 2820
     * quotaType : SELECT
     * unit :
     * quotaDesc :
     * minValue : 0
     * abnormal : true
     * detectionRange : null
     * quotaCode : XYZBZY001001
     * quotaIndex : 4
     */

    private String medical;
    private String quotaName;
    private double maxValue;
    private Object icon;
    private String quotaCatCode;
    private String quotaValue;
    private String quotaType;
    private String unit;
    private String quotaDesc;
    private double minValue;
    private boolean abnormal;
    private Object detectionRange;
    private String quotaCode;
    private int quotaIndex;
    private ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.OptionalValueListBean> optionalValueList;
    private ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.QuotaCauseListBean> quotaCauseList;

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getQuotaName() {
        return quotaName;
    }

    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getQuotaCatCode() {
        return quotaCatCode;
    }

    public void setQuotaCatCode(String quotaCatCode) {
        this.quotaCatCode = quotaCatCode;
    }

    public String getQuotaValue() {
        return quotaValue;
    }

    public void setQuotaValue(String quotaValue) {
        this.quotaValue = quotaValue;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuotaDesc() {
        return quotaDesc;
    }

    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public boolean isAbnormal() {
        return abnormal;
    }

    public void setAbnormal(boolean abnormal) {
        this.abnormal = abnormal;
    }

    public Object getDetectionRange() {
        return detectionRange;
    }

    public void setDetectionRange(Object detectionRange) {
        this.detectionRange = detectionRange;
    }

    public String getQuotaCode() {
        return quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    public int getQuotaIndex() {
        return quotaIndex;
    }

    public void setQuotaIndex(int quotaIndex) {
        this.quotaIndex = quotaIndex;
    }

    public ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.OptionalValueListBean> getOptionalValueList() {
        return optionalValueList;
    }

    public void setOptionalValueList(ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.OptionalValueListBean> optionalValueList) {
        this.optionalValueList = optionalValueList;
    }

    public ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.QuotaCauseListBean> getQuotaCauseList() {
        return quotaCauseList;
    }

    public void setQuotaCauseList(ArrayList<SubmitAnswerResult.CardInfoBean.AbnormityQuotaInfoListBean.QuotaCauseListBean> quotaCauseList) {
        this.quotaCauseList = quotaCauseList;
    }

    public static class OptionalValueListBean {
        /**
         * text : NA
         * value : -1
         * normalValue : false
         */

        private String text;
        private String value;
        private boolean normalValue;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isNormalValue() {
            return normalValue;
        }

        public void setNormalValue(boolean normalValue) {
            this.normalValue = normalValue;
        }
    }

    public static class QuotaCauseListBean {
        /**
         * expression : x==2820
         * cause : 提示脏腑精气不足。
         */

        private String expression;
        private String cause;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getCause() {
            return cause;
        }

        public void setCause(String cause) {
            this.cause = cause;
        }
    }
}
