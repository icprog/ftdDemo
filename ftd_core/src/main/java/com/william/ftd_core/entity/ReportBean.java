package com.william.ftd_core.entity;

import java.util.List;

public class ReportBean {


    /**
     * faceDiagnose : {"pulse":null,"tongue":"舌淡红，胖，有齿痕，无裂纹，无瘀点瘀斑，无点刺，无舌边尖红","face":"面色红黄隐隐、明润含蓄，唇色暗红，无局部特征，少量光泽","moss":"苔白，苔薄，无苔剥，无腐腻"}
     * checkName : 察颜观舌
     * seqNo : 2019062911221600264
     * dataType : 2
     * typeCode : FACE_TONGUE_LK
     * moss : [{"dataSource":null,"quotaValue":"1830","quotaIndex":1,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY003003","quotaText":"苔白","updated":null,"quotaName":"苔色","detectionRangeMin":"0","minValue":0,"id":"155742","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY003","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"3710","quotaIndex":2,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY003002","quotaText":"苔薄","updated":null,"quotaName":"苔厚薄","detectionRangeMin":"0","minValue":0,"id":"155741","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY003","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"3214","quotaIndex":3,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY003001","quotaText":"无苔剥","updated":null,"quotaName":"苔剥脱","detectionRangeMin":"0","minValue":0,"id":"155740","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY003","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"3330","quotaIndex":4,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY003004","quotaText":"无腐腻","updated":null,"quotaName":"苔腐腻","detectionRangeMin":"0","minValue":0,"id":"155743","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY003","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"}]
     * quotaInfoList : [{"data":[{"quotaCatName":"面","normalScore":10,"quotaValueName":"面色红黄隐隐、明润含蓄","quotaIndex":1,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"面色苍白","value":"11305"},{"score":3,"text":"面色赤、暗红","value":"11403"},{"score":4,"text":"面色黑","value":"1190"},{"score":6,"text":"面色赤","value":"1140"},{"score":6,"text":"面色白","value":"1130"},{"score":6,"text":"面色黄","value":"1120"},{"score":10,"text":"面色红黄隐隐、明润含蓄","value":"11205"}],"quotaName":"面色","quotaCode":"XYZBZY001004","quotaCatCode":"XYZBZY001","quotaValue":"11205","score":10,"checkDate":"2019-06-29 11:22:16","quotaDesc":"面色红黄隐隐、明润含蓄：正常。"},{"quotaCatName":"面","normalScore":10,"quotaValueName":"唇色暗红","quotaIndex":2,"quotaNormal":1,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"唇色紫黑","value":"13705"},{"score":2,"text":"唇色青紫","value":"13708"},{"score":3,"text":"唇色白","value":"1330"},{"score":4,"text":"唇色紫","value":"1370"},{"score":6,"text":"唇色暗红","value":"13403"},{"score":6,"text":"唇色淡","value":"1335"},{"score":10,"text":"唇色红","value":"1340"}],"quotaName":"唇色","quotaCode":"XYZBZY001003","quotaCatCode":"XYZBZY001","quotaValue":"13403","score":6,"checkDate":"2019-06-29 11:22:16","quotaDesc":"唇色暗红：主气血不畅。"},{"quotaCatName":"面","normalScore":5,"quotaValueName":"无局部特征","quotaIndex":3,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"两颧红，眼轮黑","value":"2520"},{"score":3,"text":"眼轮黑","value":"2530"},{"score":3,"text":"两颧红","value":"2510"},{"score":5,"text":"无局部特征","value":"2540"}],"quotaName":"局部特征","quotaCode":"XYZBZY001002","quotaCatCode":"XYZBZY001","quotaValue":"2540","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无局部特征：正常。"},{"quotaCatName":"面","normalScore":5,"quotaValueName":"少量光泽","quotaIndex":4,"quotaNormal":1,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"无光泽","value":"2810"},{"score":3,"text":"少量光泽","value":"2820"},{"score":5,"text":"有光泽","value":"2830"}],"quotaName":"光泽","quotaCode":"XYZBZY001001","quotaCatCode":"XYZBZY001","quotaValue":"2820","score":3,"checkDate":"2019-06-29 11:22:16","quotaDesc":"少量光泽：提示脏腑精气不足。"}],"quotaCatCode":"XYZBZY001","quotaCatName":"面"},{"data":[{"quotaCatName":"舌质","normalScore":10,"quotaValueName":"舌淡红","quotaIndex":1,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"舌紫暗","value":"16707"},{"score":2,"text":"舌绛","value":"1660"},{"score":4,"text":"舌淡紫","value":"16705"},{"score":6,"text":"舌暗红","value":"16407"},{"score":6,"text":"舌淡白","value":"16305"},{"score":8,"text":"舌红","value":"1640"},{"score":8,"text":"舌淡","value":"1635"},{"score":10,"text":"舌淡红","value":"16405"}],"quotaName":"舌色","quotaCode":"XYZBZY002006","quotaCatCode":"XYZBZY002","quotaValue":"16405","score":10,"checkDate":"2019-06-29 11:22:16","quotaDesc":"舌淡红：正常，或病中见之多主病轻。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"胖","quotaIndex":2,"quotaNormal":1,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"胖","value":"3512"},{"score":2,"text":"瘦","value":"3511"},{"score":5,"text":"胖瘦适中","value":"3513"}],"quotaName":"舌胖瘦","quotaCode":"XYZBZY002005","quotaCatCode":"XYZBZY002","quotaValue":"3512","score":2,"checkDate":"2019-06-29 11:22:16","quotaDesc":"胖：多主水湿内停、痰湿热毒上泛。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"有齿痕","quotaIndex":3,"quotaNormal":1,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"有齿痕","value":"4220"},{"score":5,"text":"无齿痕","value":"4210"}],"quotaName":"舌齿痕","quotaCode":"XYZBZY002001","quotaCatCode":"XYZBZY002","quotaValue":"4220","score":2,"checkDate":"2019-06-29 11:22:16","quotaDesc":"有齿痕：见于先天性齿痕舌，或主脾虚，水湿内盛等。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"无裂纹","quotaIndex":4,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"有裂纹","value":"4020"},{"score":5,"text":"无裂纹","value":"4010"}],"quotaName":"舌裂纹","quotaCode":"XYZBZY002003","quotaCatCode":"XYZBZY002","quotaValue":"4010","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无裂纹：正常。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"无瘀点瘀斑","quotaIndex":5,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"有瘀点或瘀斑","value":"4120"},{"score":5,"text":"无瘀点瘀斑","value":"4110"}],"quotaName":"舌瘀点","quotaCode":"XYZBZY002002","quotaCatCode":"XYZBZY002","quotaValue":"4110","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无瘀点瘀斑：正常。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"无点刺","quotaIndex":6,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"有点刺","value":"3920"},{"score":5,"text":"无点刺","value":"3910"}],"quotaName":"舌点刺","quotaCode":"XYZBZY002004","quotaCatCode":"XYZBZY002","quotaValue":"3910","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无点刺：正常。"},{"quotaCatName":"舌质","normalScore":5,"quotaValueName":"无舌边尖红","quotaIndex":7,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"舌边尖红","value":"1940"},{"score":5,"text":"无舌边尖红","value":"1910"}],"quotaName":"舌边尖","quotaCode":"XYZBZY002007","quotaCatCode":"XYZBZY002","quotaValue":"1910","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无舌边尖红：正常。"}],"quotaCatCode":"XYZBZY002","quotaCatName":"舌质"},{"data":[{"quotaCatName":"舌苔","normalScore":10,"quotaValueName":"苔白","quotaIndex":1,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"苔无","value":"1811"},{"score":2,"text":"苔灰黑","value":"18903"},{"score":6,"text":"苔少","value":"1812"},{"score":6,"text":"苔黄","value":"1820"},{"score":8,"text":"苔黄白相间","value":"18201"},{"score":10,"text":"苔白","value":"1830"}],"quotaName":"苔色","quotaCode":"XYZBZY003003","quotaCatCode":"XYZBZY003","quotaValue":"1830","score":10,"checkDate":"2019-06-29 11:22:16","quotaDesc":"苔白：正常，或病中见之多主表证、寒证、湿证或热证。"},{"quotaCatName":"舌苔","normalScore":10,"quotaValueName":"苔薄","quotaIndex":2,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"苔无","value":"1811"},{"score":6,"text":"苔厚","value":"3720"},{"score":6,"text":"苔少","value":"1812"},{"score":10,"text":"苔薄","value":"3710"}],"quotaName":"苔厚薄","quotaCode":"XYZBZY003002","quotaCatCode":"XYZBZY003","quotaValue":"3710","score":10,"checkDate":"2019-06-29 11:22:16","quotaDesc":"苔薄：正常，或病中见之多主病轻、表证。"},{"quotaCatName":"舌苔","normalScore":5,"quotaValueName":"无苔剥","quotaIndex":3,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"有苔剥","value":"3212"},{"score":5,"text":"无苔剥","value":"3214"}],"quotaName":"苔剥脱","quotaCode":"XYZBZY003001","quotaCatCode":"XYZBZY003","quotaValue":"3214","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无苔剥：正常。"},{"quotaCatName":"舌苔","normalScore":5,"quotaValueName":"无腐腻","quotaIndex":4,"quotaNormal":2,"values":[{"score":0,"text":"不适用","value":"-1"},{"score":2,"text":"苔腻","value":"3320"},{"score":3,"text":"苔腐","value":"3310"},{"score":5,"text":"无腐腻","value":"3330"}],"quotaName":"苔腐腻","quotaCode":"XYZBZY003004","quotaCatCode":"XYZBZY003","quotaValue":"3330","score":5,"checkDate":"2019-06-29 11:22:16","quotaDesc":"无腐腻：正常。"}],"quotaCatCode":"XYZBZY003","quotaCatName":"舌苔"}]
     * uqe : [{"questionCode":"Q19","userHistorySeqNo":"2019062911221600264","userId":"2466","id":"33175","updated":null,"flag":0,"created":"2019-06-29 11:22:17.0","questionContent":"★不欲饮冷"}]
     * otherUqo : []
     * score : 80.9
     * ur : [{"medical":1,"diseaseName":"脾阳虚证","diseaseId":"ZZPA20"},{"medical":1,"diseaseName":"脾肾阳虚证","diseaseId":"ZZPS80"},{"medical":1,"diseaseName":"肾气虚证","diseaseId":"ZZS030"}]
     * eight : {"yangScore":77.5,"userHistorySeqNo":"2019062911221600264","userId":"2466","id":"5551","sixDiseaseList":"[{\"code\": \"ZZS030\", \"name\": \"肾气虚证\", \"score\": 36, \"attach\": \"L\"}, {\"code\": \"ZZS030\", \"name\": \"肾气虚证\", \"score\": 36, \"attach\": \"X\"}, {\"code\": \"XYZYBZ\", \"name\": \"表寒证\", \"score\": 35, \"attach\": \"B\"}, {\"code\": \"ZBH000\", \"name\": \"寒证\", \"score\": 32.5, \"attach\": \"H\"}, {\"code\": \"ZYTM30\", \"name\": \"痰湿内蕴证\", \"score\": 32.5, \"attach\": \"S\"}, {\"code\": \"ZBMR20\", \"name\": \"湿热内蕴证\", \"score\": 10, \"attach\": \"R\"}]","totalScore":83.6,"created":"2019-06-29 11:22:18.0","yinScore":104.5}
     * qm : [{"unit":null,"maxValue":0,"normal":1,"detectionRangeMax":"0","causeList":[{"expression":"x==13403","cause":"主气血不畅。"}],"minValue":0,"code":"XYZBZY001003","detectionRangeMin":"0","normalValue":null,"text":"唇色暗红","value":"13403","cause":null,"quotaType":0,"name":"唇色"},{"unit":null,"maxValue":0,"normal":1,"detectionRangeMax":"0","causeList":[{"expression":"x==3512","cause":"多主水湿内停、痰湿热毒上泛。"}],"minValue":0,"code":"XYZBZY002005","detectionRangeMin":"0","normalValue":null,"text":"胖","value":"3512","cause":null,"quotaType":0,"name":"舌胖瘦"},{"unit":null,"maxValue":0,"normal":1,"detectionRangeMax":"0","causeList":[{"expression":"x==4220","cause":"见于先天性齿痕舌，或主脾虚，水湿内盛等。"}],"minValue":0,"code":"XYZBZY002001","detectionRangeMin":"0","normalValue":null,"text":"有齿痕","value":"4220","cause":null,"quotaType":0,"name":"舌齿痕"},{"unit":null,"maxValue":0,"normal":1,"detectionRangeMax":"0","causeList":[{"expression":"x==2820","cause":"提示脏腑精气不足。"}],"minValue":0,"code":"XYZBZY001001","detectionRangeMin":"0","normalValue":null,"text":"少量光泽","value":"2820","cause":null,"quotaType":0,"name":"光泽"}]
     * uwr : null
     * signUqo : []
     * evaluate : 建议参考具体异常指标说明，定期复查或做进一步检查，以排除疾病风险。
     * objJson : null
     * tongue : [{"dataSource":null,"quotaValue":"16405","quotaIndex":1,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002006","quotaText":"舌淡红","updated":null,"quotaName":"舌色","detectionRangeMin":"0","minValue":0,"id":"155738","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"3512","quotaIndex":2,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002005","quotaText":"胖","updated":null,"quotaName":"舌胖瘦","detectionRangeMin":"0","minValue":0,"id":"155737","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":1,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"4220","quotaIndex":3,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002001","quotaText":"有齿痕","updated":null,"quotaName":"舌齿痕","detectionRangeMin":"0","minValue":0,"id":"155733","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":1,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"4010","quotaIndex":4,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002003","quotaText":"无裂纹","updated":null,"quotaName":"舌裂纹","detectionRangeMin":"0","minValue":0,"id":"155735","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"4110","quotaIndex":5,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002002","quotaText":"无瘀点瘀斑","updated":null,"quotaName":"舌瘀点","detectionRangeMin":"0","minValue":0,"id":"155734","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"3910","quotaIndex":6,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002004","quotaText":"无点刺","updated":null,"quotaName":"舌点刺","detectionRangeMin":"0","minValue":0,"id":"155736","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"1910","quotaIndex":7,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY002007","quotaText":"无舌边尖红","updated":null,"quotaName":"舌边尖","detectionRangeMin":"0","minValue":0,"id":"155739","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY002","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"}]
     * face : [{"dataSource":null,"quotaValue":"11205","quotaIndex":1,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY001004","quotaText":"面色红黄隐隐、明润含蓄","updated":null,"quotaName":"面色","detectionRangeMin":"0","minValue":0,"id":"155732","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY001","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"13403","quotaIndex":2,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY001003","quotaText":"唇色暗红","updated":null,"quotaName":"唇色","detectionRangeMin":"0","minValue":0,"id":"155731","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY001","deviceNo":null,"quotaNormal":1,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"2540","quotaIndex":3,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY001002","quotaText":"无局部特征","updated":null,"quotaName":"局部特征","detectionRangeMin":"0","minValue":0,"id":"155730","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY001","deviceNo":null,"quotaNormal":2,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"},{"dataSource":null,"quotaValue":"2820","quotaIndex":4,"checkDate":"2019-06-29 11:22:17.0","quotaType":3,"detectionRangeMax":"0","userHistorySeqNo":"2019062911221600264","deviceSn":null,"flag":0,"quotaCode":"XYZBZY001001","quotaText":"少量光泽","updated":null,"quotaName":"光泽","detectionRangeMin":"0","minValue":0,"id":"155729","quotaUnit":null,"quotaIcon":null,"quotaCatCode":"XYZBZY001","deviceNo":null,"quotaNormal":1,"medical":1,"maxValue":0,"created":"2019-06-29 11:22:17.0","userId":"2466"}]
     * faceImg : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/b76b965d00434ee38e06c6ea5d78bdec
     * tongueImg : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/db4d6127eb2c4023a434a40cf9c49587
     * userName : 还好还好哈还好还好哈哈
     * createTime : 2019-06-29 11:22:17
     * baseOfTongueUrl : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/e9c961e63d3146819d49780e56259e11
     */

    private FaceDiagnoseBean faceDiagnose;
    private String checkName;
    private String seqNo;
    private int dataType;
    private String typeCode;
    private double score;
    private EightBean eight;
    private Object uwr;
    private String evaluate;
    private Object objJson;
    private String faceImg;
    private String tongueImg;
    private String userName;
    private String createTime;
    private String baseOfTongueUrl;
    private List<MossBean> moss;
    private List<QuotaInfoListBean> quotaInfoList;
    private List<UqeBean> uqe;
    private List<?> otherUqo;
    private List<UrBean> ur;
    private List<QmBean> qm;
    private List<?> signUqo;
    private List<TongueBean> tongue;
    private List<FaceBean> face;

    public FaceDiagnoseBean getFaceDiagnose() {
        return faceDiagnose;
    }

    public void setFaceDiagnose(FaceDiagnoseBean faceDiagnose) {
        this.faceDiagnose = faceDiagnose;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public EightBean getEight() {
        return eight;
    }

    public void setEight(EightBean eight) {
        this.eight = eight;
    }

    public Object getUwr() {
        return uwr;
    }

    public void setUwr(Object uwr) {
        this.uwr = uwr;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Object getObjJson() {
        return objJson;
    }

    public void setObjJson(Object objJson) {
        this.objJson = objJson;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getTongueImg() {
        return tongueImg;
    }

    public void setTongueImg(String tongueImg) {
        this.tongueImg = tongueImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBaseOfTongueUrl() {
        return baseOfTongueUrl;
    }

    public void setBaseOfTongueUrl(String baseOfTongueUrl) {
        this.baseOfTongueUrl = baseOfTongueUrl;
    }

    public List<MossBean> getMoss() {
        return moss;
    }

    public void setMoss(List<MossBean> moss) {
        this.moss = moss;
    }

    public List<QuotaInfoListBean> getQuotaInfoList() {
        return quotaInfoList;
    }

    public void setQuotaInfoList(List<QuotaInfoListBean> quotaInfoList) {
        this.quotaInfoList = quotaInfoList;
    }

    public List<UqeBean> getUqe() {
        return uqe;
    }

    public void setUqe(List<UqeBean> uqe) {
        this.uqe = uqe;
    }

    public List<?> getOtherUqo() {
        return otherUqo;
    }

    public void setOtherUqo(List<?> otherUqo) {
        this.otherUqo = otherUqo;
    }

    public List<UrBean> getUr() {
        return ur;
    }

    public void setUr(List<UrBean> ur) {
        this.ur = ur;
    }

    public List<QmBean> getQm() {
        return qm;
    }

    public void setQm(List<QmBean> qm) {
        this.qm = qm;
    }

    public List<?> getSignUqo() {
        return signUqo;
    }

    public void setSignUqo(List<?> signUqo) {
        this.signUqo = signUqo;
    }

    public List<TongueBean> getTongue() {
        return tongue;
    }

    public void setTongue(List<TongueBean> tongue) {
        this.tongue = tongue;
    }

    public List<FaceBean> getFace() {
        return face;
    }

    public void setFace(List<FaceBean> face) {
        this.face = face;
    }

    public static class FaceDiagnoseBean {
        /**
         * pulse : null
         * tongue : 舌淡红，胖，有齿痕，无裂纹，无瘀点瘀斑，无点刺，无舌边尖红
         * face : 面色红黄隐隐、明润含蓄，唇色暗红，无局部特征，少量光泽
         * moss : 苔白，苔薄，无苔剥，无腐腻
         */

        private Object pulse;
        private String tongue;
        private String face;
        private String moss;

        public Object getPulse() {
            return pulse;
        }

        public void setPulse(Object pulse) {
            this.pulse = pulse;
        }

        public String getTongue() {
            return tongue;
        }

        public void setTongue(String tongue) {
            this.tongue = tongue;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getMoss() {
            return moss;
        }

        public void setMoss(String moss) {
            this.moss = moss;
        }
    }

    public static class EightBean {
        /**
         * yangScore : 77.5
         * userHistorySeqNo : 2019062911221600264
         * userId : 2466
         * id : 5551
         * sixDiseaseList : [{"code": "ZZS030", "name": "肾气虚证", "score": 36, "attach": "L"}, {"code": "ZZS030", "name": "肾气虚证", "score": 36, "attach": "X"}, {"code": "XYZYBZ", "name": "表寒证", "score": 35, "attach": "B"}, {"code": "ZBH000", "name": "寒证", "score": 32.5, "attach": "H"}, {"code": "ZYTM30", "name": "痰湿内蕴证", "score": 32.5, "attach": "S"}, {"code": "ZBMR20", "name": "湿热内蕴证", "score": 10, "attach": "R"}]
         * totalScore : 83.6
         * created : 2019-06-29 11:22:18.0
         * yinScore : 104.5
         */

        private double yangScore;
        private String userHistorySeqNo;
        private String userId;
        private String id;
        private String sixDiseaseList;
        private double totalScore;
        private String created;
        private double yinScore;

        public double getYangScore() {
            return yangScore;
        }

        public void setYangScore(double yangScore) {
            this.yangScore = yangScore;
        }

        public String getUserHistorySeqNo() {
            return userHistorySeqNo;
        }

        public void setUserHistorySeqNo(String userHistorySeqNo) {
            this.userHistorySeqNo = userHistorySeqNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSixDiseaseList() {
            return sixDiseaseList;
        }

        public void setSixDiseaseList(String sixDiseaseList) {
            this.sixDiseaseList = sixDiseaseList;
        }

        public double getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(double totalScore) {
            this.totalScore = totalScore;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public double getYinScore() {
            return yinScore;
        }

        public void setYinScore(double yinScore) {
            this.yinScore = yinScore;
        }
    }

    public static class MossBean {
        /**
         * dataSource : null
         * quotaValue : 1830
         * quotaIndex : 1
         * checkDate : 2019-06-29 11:22:17.0
         * quotaType : 3
         * detectionRangeMax : 0
         * userHistorySeqNo : 2019062911221600264
         * deviceSn : null
         * flag : 0
         * quotaCode : XYZBZY003003
         * quotaText : 苔白
         * updated : null
         * quotaName : 苔色
         * detectionRangeMin : 0
         * minValue : 0
         * id : 155742
         * quotaUnit : null
         * quotaIcon : null
         * quotaCatCode : XYZBZY003
         * deviceNo : null
         * quotaNormal : 2
         * medical : 1
         * maxValue : 0
         * created : 2019-06-29 11:22:17.0
         * userId : 2466
         */

        private Object dataSource;
        private String quotaValue;
        private int quotaIndex;
        private String checkDate;
        private int quotaType;
        private String detectionRangeMax;
        private String userHistorySeqNo;
        private Object deviceSn;
        private int flag;
        private String quotaCode;
        private String quotaText;
        private Object updated;
        private String quotaName;
        private String detectionRangeMin;
        private int minValue;
        private String id;
        private Object quotaUnit;
        private Object quotaIcon;
        private String quotaCatCode;
        private Object deviceNo;
        private int quotaNormal;
        private int medical;
        private int maxValue;
        private String created;
        private String userId;

        public Object getDataSource() {
            return dataSource;
        }

        public void setDataSource(Object dataSource) {
            this.dataSource = dataSource;
        }

        public String getQuotaValue() {
            return quotaValue;
        }

        public void setQuotaValue(String quotaValue) {
            this.quotaValue = quotaValue;
        }

        public int getQuotaIndex() {
            return quotaIndex;
        }

        public void setQuotaIndex(int quotaIndex) {
            this.quotaIndex = quotaIndex;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public int getQuotaType() {
            return quotaType;
        }

        public void setQuotaType(int quotaType) {
            this.quotaType = quotaType;
        }

        public String getDetectionRangeMax() {
            return detectionRangeMax;
        }

        public void setDetectionRangeMax(String detectionRangeMax) {
            this.detectionRangeMax = detectionRangeMax;
        }

        public String getUserHistorySeqNo() {
            return userHistorySeqNo;
        }

        public void setUserHistorySeqNo(String userHistorySeqNo) {
            this.userHistorySeqNo = userHistorySeqNo;
        }

        public Object getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(Object deviceSn) {
            this.deviceSn = deviceSn;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getQuotaCode() {
            return quotaCode;
        }

        public void setQuotaCode(String quotaCode) {
            this.quotaCode = quotaCode;
        }

        public String getQuotaText() {
            return quotaText;
        }

        public void setQuotaText(String quotaText) {
            this.quotaText = quotaText;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }

        public String getQuotaName() {
            return quotaName;
        }

        public void setQuotaName(String quotaName) {
            this.quotaName = quotaName;
        }

        public String getDetectionRangeMin() {
            return detectionRangeMin;
        }

        public void setDetectionRangeMin(String detectionRangeMin) {
            this.detectionRangeMin = detectionRangeMin;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getQuotaUnit() {
            return quotaUnit;
        }

        public void setQuotaUnit(Object quotaUnit) {
            this.quotaUnit = quotaUnit;
        }

        public Object getQuotaIcon() {
            return quotaIcon;
        }

        public void setQuotaIcon(Object quotaIcon) {
            this.quotaIcon = quotaIcon;
        }

        public String getQuotaCatCode() {
            return quotaCatCode;
        }

        public void setQuotaCatCode(String quotaCatCode) {
            this.quotaCatCode = quotaCatCode;
        }

        public Object getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(Object deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getQuotaNormal() {
            return quotaNormal;
        }

        public void setQuotaNormal(int quotaNormal) {
            this.quotaNormal = quotaNormal;
        }

        public int getMedical() {
            return medical;
        }

        public void setMedical(int medical) {
            this.medical = medical;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class UqeBean {
        /**
         * questionCode : Q19
         * userHistorySeqNo : 2019062911221600264
         * userId : 2466
         * id : 33175
         * updated : null
         * flag : 0
         * created : 2019-06-29 11:22:17.0
         * questionContent : ★不欲饮冷
         */

        private String questionCode;
        private String userHistorySeqNo;
        private String userId;
        private String id;
        private Object updated;
        private int flag;
        private String created;
        private String questionContent;

        public String getQuestionCode() {
            return questionCode;
        }

        public void setQuestionCode(String questionCode) {
            this.questionCode = questionCode;
        }

        public String getUserHistorySeqNo() {
            return userHistorySeqNo;
        }

        public void setUserHistorySeqNo(String userHistorySeqNo) {
            this.userHistorySeqNo = userHistorySeqNo;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }
    }

    public static class UrBean {
        /**
         * medical : 1
         * diseaseName : 脾阳虚证
         * diseaseId : ZZPA20
         */

        private int medical;
        private String diseaseName;
        private String diseaseId;

        public int getMedical() {
            return medical;
        }

        public void setMedical(int medical) {
            this.medical = medical;
        }

        public String getDiseaseName() {
            return diseaseName;
        }

        public void setDiseaseName(String diseaseName) {
            this.diseaseName = diseaseName;
        }

        public String getDiseaseId() {
            return diseaseId;
        }

        public void setDiseaseId(String diseaseId) {
            this.diseaseId = diseaseId;
        }
    }

    public static class QmBean {
        /**
         * unit : null
         * maxValue : 0
         * normal : 1
         * detectionRangeMax : 0
         * causeList : [{"expression":"x==13403","cause":"主气血不畅。"}]
         * minValue : 0
         * code : XYZBZY001003
         * detectionRangeMin : 0
         * normalValue : null
         * text : 唇色暗红
         * value : 13403
         * cause : null
         * quotaType : 0
         * name : 唇色
         */

        private Object unit;
        private int maxValue;
        private int normal;
        private String detectionRangeMax;
        private int minValue;
        private String code;
        private String detectionRangeMin;
        private Object normalValue;
        private String text;
        private String value;
        private Object cause;
        private int quotaType;
        private String name;
        private List<CauseListBean> causeList;

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }

        public int getNormal() {
            return normal;
        }

        public void setNormal(int normal) {
            this.normal = normal;
        }

        public String getDetectionRangeMax() {
            return detectionRangeMax;
        }

        public void setDetectionRangeMax(String detectionRangeMax) {
            this.detectionRangeMax = detectionRangeMax;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDetectionRangeMin() {
            return detectionRangeMin;
        }

        public void setDetectionRangeMin(String detectionRangeMin) {
            this.detectionRangeMin = detectionRangeMin;
        }

        public Object getNormalValue() {
            return normalValue;
        }

        public void setNormalValue(Object normalValue) {
            this.normalValue = normalValue;
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

        public Object getCause() {
            return cause;
        }

        public void setCause(Object cause) {
            this.cause = cause;
        }

        public int getQuotaType() {
            return quotaType;
        }

        public void setQuotaType(int quotaType) {
            this.quotaType = quotaType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CauseListBean> getCauseList() {
            return causeList;
        }

        public void setCauseList(List<CauseListBean> causeList) {
            this.causeList = causeList;
        }

        public static class CauseListBean {
            /**
             * expression : x==13403
             * cause : 主气血不畅。
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

    public static class TongueBean {
        /**
         * dataSource : null
         * quotaValue : 16405
         * quotaIndex : 1
         * checkDate : 2019-06-29 11:22:17.0
         * quotaType : 3
         * detectionRangeMax : 0
         * userHistorySeqNo : 2019062911221600264
         * deviceSn : null
         * flag : 0
         * quotaCode : XYZBZY002006
         * quotaText : 舌淡红
         * updated : null
         * quotaName : 舌色
         * detectionRangeMin : 0
         * minValue : 0
         * id : 155738
         * quotaUnit : null
         * quotaIcon : null
         * quotaCatCode : XYZBZY002
         * deviceNo : null
         * quotaNormal : 2
         * medical : 1
         * maxValue : 0
         * created : 2019-06-29 11:22:17.0
         * userId : 2466
         */

        private Object dataSource;
        private String quotaValue;
        private int quotaIndex;
        private String checkDate;
        private int quotaType;
        private String detectionRangeMax;
        private String userHistorySeqNo;
        private Object deviceSn;
        private int flag;
        private String quotaCode;
        private String quotaText;
        private Object updated;
        private String quotaName;
        private String detectionRangeMin;
        private int minValue;
        private String id;
        private Object quotaUnit;
        private Object quotaIcon;
        private String quotaCatCode;
        private Object deviceNo;
        private int quotaNormal;
        private int medical;
        private int maxValue;
        private String created;
        private String userId;

        public Object getDataSource() {
            return dataSource;
        }

        public void setDataSource(Object dataSource) {
            this.dataSource = dataSource;
        }

        public String getQuotaValue() {
            return quotaValue;
        }

        public void setQuotaValue(String quotaValue) {
            this.quotaValue = quotaValue;
        }

        public int getQuotaIndex() {
            return quotaIndex;
        }

        public void setQuotaIndex(int quotaIndex) {
            this.quotaIndex = quotaIndex;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public int getQuotaType() {
            return quotaType;
        }

        public void setQuotaType(int quotaType) {
            this.quotaType = quotaType;
        }

        public String getDetectionRangeMax() {
            return detectionRangeMax;
        }

        public void setDetectionRangeMax(String detectionRangeMax) {
            this.detectionRangeMax = detectionRangeMax;
        }

        public String getUserHistorySeqNo() {
            return userHistorySeqNo;
        }

        public void setUserHistorySeqNo(String userHistorySeqNo) {
            this.userHistorySeqNo = userHistorySeqNo;
        }

        public Object getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(Object deviceSn) {
            this.deviceSn = deviceSn;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getQuotaCode() {
            return quotaCode;
        }

        public void setQuotaCode(String quotaCode) {
            this.quotaCode = quotaCode;
        }

        public String getQuotaText() {
            return quotaText;
        }

        public void setQuotaText(String quotaText) {
            this.quotaText = quotaText;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }

        public String getQuotaName() {
            return quotaName;
        }

        public void setQuotaName(String quotaName) {
            this.quotaName = quotaName;
        }

        public String getDetectionRangeMin() {
            return detectionRangeMin;
        }

        public void setDetectionRangeMin(String detectionRangeMin) {
            this.detectionRangeMin = detectionRangeMin;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getQuotaUnit() {
            return quotaUnit;
        }

        public void setQuotaUnit(Object quotaUnit) {
            this.quotaUnit = quotaUnit;
        }

        public Object getQuotaIcon() {
            return quotaIcon;
        }

        public void setQuotaIcon(Object quotaIcon) {
            this.quotaIcon = quotaIcon;
        }

        public String getQuotaCatCode() {
            return quotaCatCode;
        }

        public void setQuotaCatCode(String quotaCatCode) {
            this.quotaCatCode = quotaCatCode;
        }

        public Object getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(Object deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getQuotaNormal() {
            return quotaNormal;
        }

        public void setQuotaNormal(int quotaNormal) {
            this.quotaNormal = quotaNormal;
        }

        public int getMedical() {
            return medical;
        }

        public void setMedical(int medical) {
            this.medical = medical;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public static class FaceBean {
        /**
         * dataSource : null
         * quotaValue : 11205
         * quotaIndex : 1
         * checkDate : 2019-06-29 11:22:17.0
         * quotaType : 3
         * detectionRangeMax : 0
         * userHistorySeqNo : 2019062911221600264
         * deviceSn : null
         * flag : 0
         * quotaCode : XYZBZY001004
         * quotaText : 面色红黄隐隐、明润含蓄
         * updated : null
         * quotaName : 面色
         * detectionRangeMin : 0
         * minValue : 0
         * id : 155732
         * quotaUnit : null
         * quotaIcon : null
         * quotaCatCode : XYZBZY001
         * deviceNo : null
         * quotaNormal : 2
         * medical : 1
         * maxValue : 0
         * created : 2019-06-29 11:22:17.0
         * userId : 2466
         */

        private Object dataSource;
        private String quotaValue;
        private int quotaIndex;
        private String checkDate;
        private int quotaType;
        private String detectionRangeMax;
        private String userHistorySeqNo;
        private Object deviceSn;
        private int flag;
        private String quotaCode;
        private String quotaText;
        private Object updated;
        private String quotaName;
        private String detectionRangeMin;
        private int minValue;
        private String id;
        private Object quotaUnit;
        private Object quotaIcon;
        private String quotaCatCode;
        private Object deviceNo;
        private int quotaNormal;
        private int medical;
        private int maxValue;
        private String created;
        private String userId;

        public Object getDataSource() {
            return dataSource;
        }

        public void setDataSource(Object dataSource) {
            this.dataSource = dataSource;
        }

        public String getQuotaValue() {
            return quotaValue;
        }

        public void setQuotaValue(String quotaValue) {
            this.quotaValue = quotaValue;
        }

        public int getQuotaIndex() {
            return quotaIndex;
        }

        public void setQuotaIndex(int quotaIndex) {
            this.quotaIndex = quotaIndex;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public int getQuotaType() {
            return quotaType;
        }

        public void setQuotaType(int quotaType) {
            this.quotaType = quotaType;
        }

        public String getDetectionRangeMax() {
            return detectionRangeMax;
        }

        public void setDetectionRangeMax(String detectionRangeMax) {
            this.detectionRangeMax = detectionRangeMax;
        }

        public String getUserHistorySeqNo() {
            return userHistorySeqNo;
        }

        public void setUserHistorySeqNo(String userHistorySeqNo) {
            this.userHistorySeqNo = userHistorySeqNo;
        }

        public Object getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(Object deviceSn) {
            this.deviceSn = deviceSn;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getQuotaCode() {
            return quotaCode;
        }

        public void setQuotaCode(String quotaCode) {
            this.quotaCode = quotaCode;
        }

        public String getQuotaText() {
            return quotaText;
        }

        public void setQuotaText(String quotaText) {
            this.quotaText = quotaText;
        }

        public Object getUpdated() {
            return updated;
        }

        public void setUpdated(Object updated) {
            this.updated = updated;
        }

        public String getQuotaName() {
            return quotaName;
        }

        public void setQuotaName(String quotaName) {
            this.quotaName = quotaName;
        }

        public String getDetectionRangeMin() {
            return detectionRangeMin;
        }

        public void setDetectionRangeMin(String detectionRangeMin) {
            this.detectionRangeMin = detectionRangeMin;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getQuotaUnit() {
            return quotaUnit;
        }

        public void setQuotaUnit(Object quotaUnit) {
            this.quotaUnit = quotaUnit;
        }

        public Object getQuotaIcon() {
            return quotaIcon;
        }

        public void setQuotaIcon(Object quotaIcon) {
            this.quotaIcon = quotaIcon;
        }

        public String getQuotaCatCode() {
            return quotaCatCode;
        }

        public void setQuotaCatCode(String quotaCatCode) {
            this.quotaCatCode = quotaCatCode;
        }

        public Object getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(Object deviceNo) {
            this.deviceNo = deviceNo;
        }

        public int getQuotaNormal() {
            return quotaNormal;
        }

        public void setQuotaNormal(int quotaNormal) {
            this.quotaNormal = quotaNormal;
        }

        public int getMedical() {
            return medical;
        }

        public void setMedical(int medical) {
            this.medical = medical;
        }

        public int getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
