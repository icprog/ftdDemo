package com.william.ftdui.constant;

import android.support.annotation.IntDef;
import android.util.SparseArray;

public class Constant {

    public static final int STEP_FACE = 0;
    public static final int STEP_TONGUE_TOP = 1;
    public static final int STEP_TONGUE_BOTTOM = 2;
    public static final int STEP_ASK = 3;

    @IntDef({STEP_FACE,STEP_TONGUE_TOP,STEP_TONGUE_BOTTOM,STEP_ASK})
    public @interface StepId{}

    public static final String FILE_NAME_FACE = "FILE_NAME_FACE.jpeg";
    public static final String FILE_NAME_TONGUE_TOP = "FILE_NAME_TONGUE_TOP.jpeg";
    public static final String FILE_NAME_TONGUE_BOTTOM = "FILE_NAME_TONGUE_BOTTOM.jpeg";

    public static SparseArray<Step> steps = new SparseArray<Step>();

    static {
        steps.put(STEP_FACE, new Step(STEP_FACE));
        steps.put(STEP_TONGUE_TOP, new Step(STEP_TONGUE_TOP));
        steps.put(STEP_TONGUE_BOTTOM, new Step(STEP_TONGUE_BOTTOM));
    }

    //体质
    public static final int TRACE_ZHI = 1;
    //体证
    public static final int TRACE_ZHENG = 2;

    @IntDef({TRACE_ZHI,TRACE_ZHENG})
    public @interface Trace{}
}
