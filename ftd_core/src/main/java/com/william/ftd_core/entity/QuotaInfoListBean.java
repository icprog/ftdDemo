package com.william.ftd_core.entity;

import java.util.List;

public class QuotaInfoListBean {
    /**
     * quotaCatName : 面
     * data : [{"score":6,"quotaDesc":"面色白：主虚证，寒证，失血等。","medical":1,"quotaName":"面色","normalScore":10,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"面色苍白","value":"11305"},{"score":3,"text":"面色赤、暗红","value":"11403"},{"score":4,"text":"面色黑","value":"1190"},{"score":6,"text":"面色赤","value":"1140"},{"score":6,"text":"面色白","value":"1130"},{"score":6,"text":"面色黄","value":"1120"},{"score":10,"text":"面色红黄隐隐、明润含蓄","value":"11205"}],"index":1,"quotaValueName":"面色白","quotaCode":"XYZBZY001004","quotaValue":"1130"},{"score":10,"quotaDesc":"唇色红：正常。","medical":1,"quotaName":"唇色","normalScore":10,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"唇色紫黑","value":"13705"},{"score":2,"text":"唇色青紫","value":"13708"},{"score":3,"text":"唇色白","value":"1330"},{"score":4,"text":"唇色紫","value":"1370"},{"score":6,"text":"唇色暗红","value":"13403"},{"score":6,"text":"唇色淡","value":"1335"},{"score":10,"text":"唇色红","value":"1340"}],"index":2,"quotaValueName":"唇色红","quotaCode":"XYZBZY001003","quotaValue":"1340"},{"score":5,"quotaDesc":"无局部特征：正常。","medical":1,"quotaName":"局部特征","normalScore":5,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"两颧红，眼轮黑","value":"2520"},{"score":3,"text":"眼轮黑","value":"2530"},{"score":3,"text":"两颧红","value":"2510"},{"score":5,"text":"无局部特征","value":"2540"}],"index":3,"quotaValueName":"无局部特征","quotaCode":"XYZBZY001002","quotaValue":"2540"},{"score":3,"quotaDesc":"少量光泽：提示脏腑精气不足。","medical":1,"quotaName":"光泽","normalScore":5,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"无光泽","value":"2810"},{"score":3,"text":"少量光泽","value":"2820"},{"score":5,"text":"有光泽","value":"2830"}],"index":4,"quotaValueName":"少量光泽","quotaCode":"XYZBZY001001","quotaValue":"2820"}]
     * quotaCatCode : XYZBZY001
     */

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
}