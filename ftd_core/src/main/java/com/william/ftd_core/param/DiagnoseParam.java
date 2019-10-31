package com.william.ftd_core.param;

import android.util.ArrayMap;

import com.william.ftd_core.Util;
import com.william.ftd_core.entity.UploadResult;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import java.io.File;

public class DiagnoseParam {

    private File file;
    private int diagnoseTag;
    private final String equCode = "02:00:00:00:00:00";
    private final String mApi = "7";
    private String scheme_flag;
    private String userId;
    private String username;
    private final String userType = "1";
    private UploadResult result;
    private FtdException exception;


//    public volatile static ArrayMap<String, DiagnoseParam> RESULT_MAP = new ArrayMap<>();
//    public static String DIAGNOSE_FACE = "0";
//    public static String DIAGNOSE_TONGUE_TOP = "1";
//    public static String DIAGNOSE_TONGUE_BOTTOM = "2";
//    static {
//        RESULT_MAP.put(DIAGNOSE_FACE, null);
//        RESULT_MAP.put(DIAGNOSE_TONGUE_TOP, null);
//        RESULT_MAP.put(DIAGNOSE_TONGUE_BOTTOM, null);
//    }

    public DiagnoseParam(File file,int diagnoseTag) {
        this.file = file;
        this.diagnoseTag = diagnoseTag;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getDiagnoseTag() {
        return diagnoseTag;
    }

    public void setDiagnoseTag(int diagnoseTag) {
        this.diagnoseTag = diagnoseTag;
    }

    public String getEquCode() {
        return equCode;
    }

    public String getCheck_id() {
        return Util.getUUID();
    }


    public String getmApi() {
        return mApi;
    }


    public String getScheme_flag() {
        return scheme_flag;
    }

    public void setScheme_flag(String scheme_flag) {
        this.scheme_flag = scheme_flag;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }


    public String getUserType() {
        return userType;
    }

    public void setUser(User user){
        this.username = user.getMoble();
        this.userId = user.getPhrId();
    }

    public UploadResult getResult() {
        return result;
    }

    public void setResult(UploadResult result) {
        this.result = result;
    }

    public FtdException getException() {
        return exception;
    }

    public void setException(FtdException exception) {
        this.exception = exception;
    }
}
