package com.william.ftd_core.param;

public class GetTendencyParam {


    /**
     * pageSize : 7
     * currentPage : 1
     * checkTypeCode : 'FACE_TONGUE_LK'
     */

    private int pageSize = 7;
    private int currentPage = 1;
    private String checkTypeCode = "'FACE_TONGUE_LK'";

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }
}
