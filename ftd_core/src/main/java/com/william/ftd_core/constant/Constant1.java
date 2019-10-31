package com.william.ftd_core.constant;

import android.util.SparseArray;

public class Constant1 {
    public static final int FACE = 0;
    public static final int TONGUE_TOP = 1;
    public static final int TONGUE_BOTTOM = 2;


    public static SparseArray<Integer> TYPES = new SparseArray<>();

    static {
        TYPES.put(FACE, FACE);
        TYPES.put(TONGUE_TOP, TONGUE_TOP);
        TYPES.put(TONGUE_BOTTOM, TONGUE_BOTTOM);
    }
}
