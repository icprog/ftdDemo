package com.william.ftdui;

import android.util.SparseIntArray;

public class Constant {

    public static int STEP_FACE = 0;
    public static int STEP_TONGUE_TOP = 1;
    public static int STEP_TONGUE_BOTTOM = 2;
    public static int STEP_ASK = 3;

    public static SparseIntArray steps = new SparseIntArray();

    static {
        steps.put(STEP_FACE, R.drawable.xuxian_mian);
        steps.put(STEP_TONGUE_TOP, R.drawable.xuxian_she);
        steps.put(STEP_TONGUE_BOTTOM, R.drawable.xuxian_shedi);
    }
}
