package com.william.ftd_base.constant;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;
import android.support.v4.util.ArrayMap;

public class Constant {

    public static final String STEP_FACE = "STEP_FACE";
    public static final String STEP_TONGUE_TOP = "STEP_TONGUE_TOP";
    public static final String STEP_TONGUE_BOTTOM = "STEP_TONGUE_BOTTOM";
    public static final String STEP_ASK = "STEP_ASK";

    @StringDef({STEP_FACE, STEP_TONGUE_TOP, STEP_TONGUE_BOTTOM, STEP_ASK})
    public @interface StepId {
    }

    public static final String FILE_NAME_FACE = "FILE_NAME_FACE.jpeg";
    public static final String FILE_NAME_TONGUE_TOP = "FILE_NAME_TONGUE_TOP.jpeg";
    public static final String FILE_NAME_TONGUE_BOTTOM = "FILE_NAME_TONGUE_BOTTOM.jpeg";

    public static ArrayMap<String,Step> steps = new ArrayMap<>();

    static {
        steps.put(STEP_FACE, new Step(STEP_FACE));
        steps.put(STEP_TONGUE_TOP, new Step(STEP_TONGUE_TOP));
        steps.put(STEP_TONGUE_BOTTOM, new Step(STEP_TONGUE_BOTTOM));
    }

    //体质
    public static final int TRACE_ZHI = 1;
    //体证
    public static final int TRACE_ZHENG = 2;

    @IntDef({TRACE_ZHI, TRACE_ZHENG})
    public @interface Trace {
    }
}
