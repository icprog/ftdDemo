package com.william.ftd_core.entity;

import java.util.ArrayList;

public class CardInfoBean {

    private String traceId;
    private ImagesBean images;//照片
    private long seqNo;
    private boolean asking;
    private String createTime;
    private EvaluationScoreBean evaluationScore;//评分
    private ArrayList<QuotaInfoListBean> quotaInfoList;//体质列表
    private ArrayList<QuestionBean> askingItemList;//问题列表
    private ArrayList<DiseaseListBean> diseaseList;//疾病列表
    private ArrayList<AbnormityQuotaInfoListBean> abnormityQuotaInfoList;

    private SixDiseaseResultBean sixDiseaseResult;//6种疾病列表
    private ArrayList<DiseaseListBean> possibleDiseaseList;//可能疾病列表

    private Object eastScoreList; // todo 这是啥？

    public SixDiseaseResultBean getSixDiseaseResult() {
        return sixDiseaseResult;
    }

    public void setSixDiseaseResult(SixDiseaseResultBean sixDiseaseResult) {
        this.sixDiseaseResult = sixDiseaseResult;
    }

    public ArrayList<DiseaseListBean> getPossibleDiseaseList() {
        return possibleDiseaseList;
    }

    public void setPossibleDiseaseList(ArrayList<DiseaseListBean> possibleDiseaseList) {
        this.possibleDiseaseList = possibleDiseaseList;
    }

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

    public long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(long seqNo) {
        this.seqNo = seqNo;
    }

    public boolean isAsking() {
        return asking;
    }

    public void setAsking(boolean asking) {
        this.asking = asking;
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

    public Object getEastScoreList() {
        return eastScoreList;
    }

    public void setEastScoreList(Object eastScoreList) {
        this.eastScoreList = eastScoreList;
    }

    public ArrayList<QuotaInfoListBean> getQuotaInfoList() {
        return quotaInfoList;
    }

    public void setQuotaInfoList(ArrayList<QuotaInfoListBean> quotaInfoList) {
        this.quotaInfoList = quotaInfoList;
    }

    public ArrayList<QuestionBean> getAskingItemList() {
        return askingItemList;
    }

    public void setAskingItemList(ArrayList<QuestionBean> askingItemList) {
        this.askingItemList = askingItemList;
    }

    public ArrayList<AbnormityQuotaInfoListBean> getAbnormityQuotaInfoList() {
        return abnormityQuotaInfoList;
    }

    public void setAbnormityQuotaInfoList(ArrayList<AbnormityQuotaInfoListBean> abnormityQuotaInfoList) {
        this.abnormityQuotaInfoList = abnormityQuotaInfoList;
    }

    public ArrayList<DiseaseListBean> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(ArrayList<DiseaseListBean> diseaseList) {
        this.diseaseList = diseaseList;
    }



}
