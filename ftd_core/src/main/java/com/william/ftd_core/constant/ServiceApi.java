package com.william.ftd_core.constant;

import android.support.annotation.IntDef;

public interface ServiceApi {

    String GET_THIRD_TOKEN = "users/third/getThirdToken";
    String LOGIN = "users/third/login";
    String PIC_UPLOAD = "OvationHealth/FaceTongueCheckServlet";

    String JSON_MEDIA = "application/json";

    int FACE = 0;
    int TONGUE_TOP = 1;
    int TONGUE_BOTTOM = 2;
    @IntDef({FACE,TONGUE_TOP,TONGUE_BOTTOM})
    @interface Type{}

    String MAC = "02:00:00:00:00:00";
}
