package com.william.ftd_core.constant;

//import android.support.annotation.IntDef;

import com.william.ftd_core.BuildConfig;

public interface ServiceApi {

    String APP_ID = "AppId";
    String APP_CODE = "appCode";
    String App_KEY = "appkey";
    String PLACE_COMPANY_ID = "PlaceCompanyId";
    String PLACE_COMPANY_P_ID = "PlaceCompanyPid";
    String COMPANY_CODE = "companyCode";

    String GET_THIRD_TOKEN = "users/third/getThirdToken";
    String LOGIN = "users/third/login";
    String PIC_UPLOAD = BuildConfig.BRAIN_IMG_UPLOAD_URL + "OvationHealth/FaceTongueCheckServlet";
    String PIC_UPLOAD1 = "api/testing/step/testingStep";

    String GET_QUESTION = "api/testing/step/getDiagnosis";
    String SUBMIT_ANSWER = "api/testing/step/getAskIng";
    //    String GET_LAST_REPORT = "api/phr/getLatestReportData";
    String GET_LAST_REPORT = "api/phr/exame/getLatestRecordBySeqNo";
    String GET_ARTICLE = "/api/ftdw/admin/getInfoListByTag";//好文
    String RECIPE = "/api/knowledge/health/dietary";//食谱
    String GET_RAISES = "/api/knowledge/health/getRaises";//四养

    String LK_TOKEN = "LkToken";

    String JSON_MEDIA = "application/json";

    int FACE = 0;
    int TONGUE_TOP = 1;
    int TONGUE_BOTTOM = 2;

//    @IntDef({FACE, TONGUE_TOP, TONGUE_BOTTOM})
//    @interface Type {
//    }

    String MAC = "02:00:00:00:00:00";
}
