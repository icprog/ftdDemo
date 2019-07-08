package com.william.ftd_core.entity;

import java.util.ArrayList;

public class TendencyResult {

    private int pageCount;
    private int previousPage;
    private boolean lastPage;
    private int nextPage;
    private int pageSize;
    private int totalCount;
    private Object extInfo;
    private String unit;
    private int startIndex;
    private boolean firstPage;
    private int endIndex;
    private int currentPage;
    private ArrayList<ResultBean> result;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * date : 2019-07-06
         * score : 72.5
         * faceUrl : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/621cfb2122b74f1fa15d6577f54a3170
         * disease : 脾肾阳虚证->ZZPS80,脾阳虚证->ZZPA20,寒证->ZBH000
         * seqNo : 2019070615494600136
         * id : 15880
         * tongueUrl : http://lk-app-dl-pro.cloud.enndata.cn/api/v1/common/fileDownload/499df3d0f56346e5ba3c84f5292624d6
         */

        private String date;
        private double score;
        private String faceUrl;
        private String disease;
        private String seqNo;
        private String id;
        private String tongueUrl;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public String getSeqNo() {
            return seqNo;
        }

        public void setSeqNo(String seqNo) {
            this.seqNo = seqNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTongueUrl() {
            return tongueUrl;
        }

        public void setTongueUrl(String tongueUrl) {
            this.tongueUrl = tongueUrl;
        }
    }
}
