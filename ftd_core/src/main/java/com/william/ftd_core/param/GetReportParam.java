package com.william.ftd_core.param;

public class GetReportParam {
    private String userId;
    private Data data;

    public GetReportParam(String phrId, long seqNo) {
        this.userId = phrId;
        this.data = new Data(seqNo);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private long seqNo;

        public Data(long seqNo) {
            this.seqNo = seqNo;
        }

        public long getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(long seqNo) {
            this.seqNo = seqNo;
        }
    }
}
