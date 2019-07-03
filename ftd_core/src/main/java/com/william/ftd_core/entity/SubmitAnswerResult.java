package com.william.ftd_core.entity;

import java.util.List;

public class SubmitAnswerResult {

    private CardInfoBean cardInfo;
    private ConstitutionInfoBean constitutionInfo;

    public CardInfoBean getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfoBean cardInfo) {
        this.cardInfo = cardInfo;
    }

    public ConstitutionInfoBean getConstitutionInfo() {
        return constitutionInfo;
    }

    public void setConstitutionInfo(ConstitutionInfoBean constitutionInfo) {
        this.constitutionInfo = constitutionInfo;
    }

    public static class CardInfoBean {


        private String traceId;
        private ImagesBean images;
        private String seqNo;
        private String createTime;
        private EvaluationScoreBean evaluationScore;
        private SixDiseaseResultBean sixDiseaseResult;
        private List<QuotaInfoListBean> quotaInfoList;
        private List<AbnormityQuotaInfoListBean> abnormityQuotaInfoList;
        private List<AskingItemListBean> askingItemList;
        private List<DiseaseListBean> diseaseList;
        private List<PossibleDiseaseListBean> possibleDiseaseList;

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public EvaluationScoreBean getEvaluationScore() {
            return evaluationScore;
        }

        public void setEvaluationScore(EvaluationScoreBean evaluationScore) {
            this.evaluationScore = evaluationScore;
        }

        public SixDiseaseResultBean getSixDiseaseResult() {
            return sixDiseaseResult;
        }

        public void setSixDiseaseResult(SixDiseaseResultBean sixDiseaseResult) {
            this.sixDiseaseResult = sixDiseaseResult;
        }

        public List<QuotaInfoListBean> getQuotaInfoList() {
            return quotaInfoList;
        }

        public void setQuotaInfoList(List<QuotaInfoListBean> quotaInfoList) {
            this.quotaInfoList = quotaInfoList;
        }

        public List<AbnormityQuotaInfoListBean> getAbnormityQuotaInfoList() {
            return abnormityQuotaInfoList;
        }

        public void setAbnormityQuotaInfoList(List<AbnormityQuotaInfoListBean> abnormityQuotaInfoList) {
            this.abnormityQuotaInfoList = abnormityQuotaInfoList;
        }

        public List<AskingItemListBean> getAskingItemList() {
            return askingItemList;
        }

        public void setAskingItemList(List<AskingItemListBean> askingItemList) {
            this.askingItemList = askingItemList;
        }

        public List<DiseaseListBean> getDiseaseList() {
            return diseaseList;
        }

        public void setDiseaseList(List<DiseaseListBean> diseaseList) {
            this.diseaseList = diseaseList;
        }

        public List<PossibleDiseaseListBean> getPossibleDiseaseList() {
            return possibleDiseaseList;
        }

        public void setPossibleDiseaseList(List<PossibleDiseaseListBean> possibleDiseaseList) {
            this.possibleDiseaseList = possibleDiseaseList;
        }

        public static class ImagesBean {
            /**
             * face : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/b76b965d00434ee38e06c6ea5d78bdec
             * tong : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/db4d6127eb2c4023a434a40cf9c49587
             * baseOfTongue : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/e9c961e63d3146819d49780e56259e11
             */

            private String face;
            private String tong;
            private String baseOfTongue;

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getTong() {
                return tong;
            }

            public void setTong(String tong) {
                this.tong = tong;
            }

            public String getBaseOfTongue() {
                return baseOfTongue;
            }

            public void setBaseOfTongue(String baseOfTongue) {
                this.baseOfTongue = baseOfTongue;
            }
        }

        public static class EvaluationScoreBean {

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

        public static class SixDiseaseResultBean {

            private double yinScore;
            private double yangScore;
            private double totalScore;
            private List<SixDiseaseListBean> sixDiseaseList;

            public double getYinScore() {
                return yinScore;
            }

            public void setYinScore(double yinScore) {
                this.yinScore = yinScore;
            }

            public double getYangScore() {
                return yangScore;
            }

            public void setYangScore(double yangScore) {
                this.yangScore = yangScore;
            }

            public double getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(double totalScore) {
                this.totalScore = totalScore;
            }

            public List<SixDiseaseListBean> getSixDiseaseList() {
                return sixDiseaseList;
            }

            public void setSixDiseaseList(List<SixDiseaseListBean> sixDiseaseList) {
                this.sixDiseaseList = sixDiseaseList;
            }

            public static class SixDiseaseListBean {

                private double score;
                private String code;
                private String name;
                private String attach;

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAttach() {
                    return attach;
                }

                public void setAttach(String attach) {
                    this.attach = attach;
                }
            }
        }

        public static class QuotaInfoListBean {
            private String quotaCatName;
            private String quotaCatCode;
            private List<DataBean> data;

            public String getQuotaCatName() {
                return quotaCatName;
            }

            public void setQuotaCatName(String quotaCatName) {
                this.quotaCatName = quotaCatName;
            }

            public String getQuotaCatCode() {
                return quotaCatCode;
            }

            public void setQuotaCatCode(String quotaCatCode) {
                this.quotaCatCode = quotaCatCode;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {

                private double score;
                private String quotaDesc;
                private int medical;
                private String quotaName;
                private double normalScore;
                private int index;
                private String quotaValueName;
                private String quotaCode;
                private String quotaValue;
                private List<ValuesBean> values;

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public String getQuotaDesc() {
                    return quotaDesc;
                }

                public void setQuotaDesc(String quotaDesc) {
                    this.quotaDesc = quotaDesc;
                }

                public int getMedical() {
                    return medical;
                }

                public void setMedical(int medical) {
                    this.medical = medical;
                }

                public String getQuotaName() {
                    return quotaName;
                }

                public void setQuotaName(String quotaName) {
                    this.quotaName = quotaName;
                }

                public double getNormalScore() {
                    return normalScore;
                }

                public void setNormalScore(double normalScore) {
                    this.normalScore = normalScore;
                }

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getQuotaValueName() {
                    return quotaValueName;
                }

                public void setQuotaValueName(String quotaValueName) {
                    this.quotaValueName = quotaValueName;
                }

                public String getQuotaCode() {
                    return quotaCode;
                }

                public void setQuotaCode(String quotaCode) {
                    this.quotaCode = quotaCode;
                }

                public String getQuotaValue() {
                    return quotaValue;
                }

                public void setQuotaValue(String quotaValue) {
                    this.quotaValue = quotaValue;
                }

                public List<ValuesBean> getValues() {
                    return values;
                }

                public void setValues(List<ValuesBean> values) {
                    this.values = values;
                }

                public static class ValuesBean {

                    private double score;
                    private String text;
                    private String value;

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }

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
                }
            }
        }

        public static class AbnormityQuotaInfoListBean {
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
            private int maxValue;
            private Object icon;
            private String quotaCatCode;
            private String quotaValue;
            private String quotaType;
            private String unit;
            private String quotaDesc;
            private int minValue;
            private boolean abnormal;
            private Object detectionRange;
            private String quotaCode;
            private int quotaIndex;
            private List<OptionalValueListBean> optionalValueList;
            private List<QuotaCauseListBean> quotaCauseList;

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

            public int getMaxValue() {
                return maxValue;
            }

            public void setMaxValue(int maxValue) {
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

            public int getMinValue() {
                return minValue;
            }

            public void setMinValue(int minValue) {
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

            public List<OptionalValueListBean> getOptionalValueList() {
                return optionalValueList;
            }

            public void setOptionalValueList(List<OptionalValueListBean> optionalValueList) {
                this.optionalValueList = optionalValueList;
            }

            public List<QuotaCauseListBean> getQuotaCauseList() {
                return quotaCauseList;
            }

            public void setQuotaCauseList(List<QuotaCauseListBean> quotaCauseList) {
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

        public static class AskingItemListBean {
            /**
             * code : Q19
             * content : ★不欲饮冷
             */

            private String code;
            private String content;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class DiseaseListBean {


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

            public void setArterialScore(int arterialScore) {
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

        public static class PossibleDiseaseListBean {

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
    }

    public static class ConstitutionInfoBean {

        private String traceId;
        private ImagesBeanX images;
        private String seqNo;
        private String createTime;
        private EvaluationScoreBeanX evaluationScore;
        private SixDiseaseResultBeanX sixDiseaseResult;
        private List<QuotaInfoListBeanX> quotaInfoList;
        private List<AbnormityQuotaInfoListBeanX> abnormityQuotaInfoList;
        private List<AskingItemListBeanX> askingItemList;
        private List<DiseaseListBeanX> diseaseList;
        private List<PossibleDiseaseListBeanX> possibleDiseaseList;

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public ImagesBeanX getImages() {
            return images;
        }

        public void setImages(ImagesBeanX images) {
            this.images = images;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public EvaluationScoreBeanX getEvaluationScore() {
            return evaluationScore;
        }

        public void setEvaluationScore(EvaluationScoreBeanX evaluationScore) {
            this.evaluationScore = evaluationScore;
        }

        public SixDiseaseResultBeanX getSixDiseaseResult() {
            return sixDiseaseResult;
        }

        public void setSixDiseaseResult(SixDiseaseResultBeanX sixDiseaseResult) {
            this.sixDiseaseResult = sixDiseaseResult;
        }

        public List<QuotaInfoListBeanX> getQuotaInfoList() {
            return quotaInfoList;
        }

        public void setQuotaInfoList(List<QuotaInfoListBeanX> quotaInfoList) {
            this.quotaInfoList = quotaInfoList;
        }

        public List<AbnormityQuotaInfoListBeanX> getAbnormityQuotaInfoList() {
            return abnormityQuotaInfoList;
        }

        public void setAbnormityQuotaInfoList(List<AbnormityQuotaInfoListBeanX> abnormityQuotaInfoList) {
            this.abnormityQuotaInfoList = abnormityQuotaInfoList;
        }

        public List<AskingItemListBeanX> getAskingItemList() {
            return askingItemList;
        }

        public void setAskingItemList(List<AskingItemListBeanX> askingItemList) {
            this.askingItemList = askingItemList;
        }

        public List<DiseaseListBeanX> getDiseaseList() {
            return diseaseList;
        }

        public void setDiseaseList(List<DiseaseListBeanX> diseaseList) {
            this.diseaseList = diseaseList;
        }

        public List<PossibleDiseaseListBeanX> getPossibleDiseaseList() {
            return possibleDiseaseList;
        }

        public void setPossibleDiseaseList(List<PossibleDiseaseListBeanX> possibleDiseaseList) {
            this.possibleDiseaseList = possibleDiseaseList;
        }

        public static class ImagesBeanX {
            /**
             * face : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/b76b965d00434ee38e06c6ea5d78bdec
             * tong : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/db4d6127eb2c4023a434a40cf9c49587
             * baseOfTongue : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/e9c961e63d3146819d49780e56259e11
             */

            private String face;
            private String tong;
            private String baseOfTongue;

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public String getTong() {
                return tong;
            }

            public void setTong(String tong) {
                this.tong = tong;
            }

            public String getBaseOfTongue() {
                return baseOfTongue;
            }

            public void setBaseOfTongue(String baseOfTongue) {
                this.baseOfTongue = baseOfTongue;
            }
        }

        public static class EvaluationScoreBeanX {

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

        public static class SixDiseaseResultBeanX {

            private double yinScore;
            private double yangScore;
            private double totalScore;
            private List<SixDiseaseListBeanX> sixDiseaseList;

            public double getYinScore() {
                return yinScore;
            }

            public void setYinScore(double yinScore) {
                this.yinScore = yinScore;
            }

            public double getYangScore() {
                return yangScore;
            }

            public void setYangScore(double yangScore) {
                this.yangScore = yangScore;
            }

            public double getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(double totalScore) {
                this.totalScore = totalScore;
            }

            public List<SixDiseaseListBeanX> getSixDiseaseList() {
                return sixDiseaseList;
            }

            public void setSixDiseaseList(List<SixDiseaseListBeanX> sixDiseaseList) {
                this.sixDiseaseList = sixDiseaseList;
            }

            public static class SixDiseaseListBeanX {
                private double score;
                private String code;
                private String name;
                private String attach;

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAttach() {
                    return attach;
                }

                public void setAttach(String attach) {
                    this.attach = attach;
                }
            }
        }

        public static class QuotaInfoListBeanX {

            private String quotaCatName;
            private String quotaCatCode;
            private List<DataBeanX> data;

            public String getQuotaCatName() {
                return quotaCatName;
            }

            public void setQuotaCatName(String quotaCatName) {
                this.quotaCatName = quotaCatName;
            }

            public String getQuotaCatCode() {
                return quotaCatCode;
            }

            public void setQuotaCatCode(String quotaCatCode) {
                this.quotaCatCode = quotaCatCode;
            }

            public List<DataBeanX> getData() {
                return data;
            }

            public void setData(List<DataBeanX> data) {
                this.data = data;
            }

            public static class DataBeanX {

                private double score;
                private String quotaDesc;
                private int medical;
                private String quotaName;
                private double normalScore;
                private int index;
                private String quotaValueName;
                private String quotaCode;
                private String quotaValue;
                private List<ValuesBeanX> values;

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
                }

                public String getQuotaDesc() {
                    return quotaDesc;
                }

                public void setQuotaDesc(String quotaDesc) {
                    this.quotaDesc = quotaDesc;
                }

                public int getMedical() {
                    return medical;
                }

                public void setMedical(int medical) {
                    this.medical = medical;
                }

                public String getQuotaName() {
                    return quotaName;
                }

                public void setQuotaName(String quotaName) {
                    this.quotaName = quotaName;
                }

                public double getNormalScore() {
                    return normalScore;
                }

                public void setNormalScore(double normalScore) {
                    this.normalScore = normalScore;
                }

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getQuotaValueName() {
                    return quotaValueName;
                }

                public void setQuotaValueName(String quotaValueName) {
                    this.quotaValueName = quotaValueName;
                }

                public String getQuotaCode() {
                    return quotaCode;
                }

                public void setQuotaCode(String quotaCode) {
                    this.quotaCode = quotaCode;
                }

                public String getQuotaValue() {
                    return quotaValue;
                }

                public void setQuotaValue(String quotaValue) {
                    this.quotaValue = quotaValue;
                }

                public List<ValuesBeanX> getValues() {
                    return values;
                }

                public void setValues(List<ValuesBeanX> values) {
                    this.values = values;
                }

                public static class ValuesBeanX {
                    private double score;
                    private String text;
                    private String value;

                    public double getScore() {
                        return score;
                    }

                    public void setScore(double score) {
                        this.score = score;
                    }

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
                }
            }
        }

        public static class AbnormityQuotaInfoListBeanX {
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
            private int maxValue;
            private Object icon;
            private String quotaCatCode;
            private String quotaValue;
            private String quotaType;
            private String unit;
            private String quotaDesc;
            private int minValue;
            private boolean abnormal;
            private Object detectionRange;
            private String quotaCode;
            private int quotaIndex;
            private List<OptionalValueListBeanX> optionalValueList;
            private List<QuotaCauseListBeanX> quotaCauseList;

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

            public int getMaxValue() {
                return maxValue;
            }

            public void setMaxValue(int maxValue) {
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

            public int getMinValue() {
                return minValue;
            }

            public void setMinValue(int minValue) {
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

            public List<OptionalValueListBeanX> getOptionalValueList() {
                return optionalValueList;
            }

            public void setOptionalValueList(List<OptionalValueListBeanX> optionalValueList) {
                this.optionalValueList = optionalValueList;
            }

            public List<QuotaCauseListBeanX> getQuotaCauseList() {
                return quotaCauseList;
            }

            public void setQuotaCauseList(List<QuotaCauseListBeanX> quotaCauseList) {
                this.quotaCauseList = quotaCauseList;
            }

            public static class OptionalValueListBeanX {
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

            public static class QuotaCauseListBeanX {
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

        public static class AskingItemListBeanX {
            /**
             * code : Q24
             * content : ★打喷嚏，鼻塞，流涕，怕冷，头痛★无咽痛，无汗
             */

            private String code;
            private String content;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class DiseaseListBeanX {
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

        public static class PossibleDiseaseListBeanX {
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
    }
}
