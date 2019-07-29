package com.william.ftd_hybrid.constant;

import com.william.ftd_core.constant.ServiceApi;
import com.william.ftdui.R;


public class Step {

    private int type;
    private int drawableId;
    private String fileName;

    public Step(@Constant.StepId int stepId) {
        switch (stepId) {
            case Constant.STEP_FACE:
                type = ServiceApi.FACE;
                drawableId = R.drawable.xuxian_mian;
                fileName = Constant.FILE_NAME_FACE;
                break;
            case Constant.STEP_TONGUE_TOP:
                type = ServiceApi.TONGUE_TOP;
                drawableId = R.drawable.xuxian_she;
                fileName = Constant.FILE_NAME_TONGUE_TOP;
                break;
            case Constant.STEP_TONGUE_BOTTOM:
                type = ServiceApi.TONGUE_BOTTOM;
                drawableId = R.drawable.xuxian_shedi;
                fileName = Constant.FILE_NAME_TONGUE_BOTTOM;
                break;
            default:
        }
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getFileName() {
        return fileName;
    }
}
