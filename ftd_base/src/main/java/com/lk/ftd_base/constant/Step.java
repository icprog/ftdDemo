package com.lk.ftd_base.constant;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

import com.lk.ftd_base.R;
import com.lk.ftd_core.constant.Constant1;

public class Step implements Parcelable {

    public volatile static SparseArray< Step> stepMap = new SparseArray<>();
//    public static final String STEP_FACE = "STEP_FACE";
//    public static final String STEP_TONGUE_TOP = "STEP_TONGUE_TOP";
//    public static final String STEP_TONGUE_BOTTOM = "STEP_TONGUE_BOTTOM";

//    @StringDef({STEP_FACE, STEP_TONGUE_TOP, STEP_TONGUE_BOTTOM})
//    public @interface StepId {
//    }

    static {
//        stepMap.put(STEP_FACE, new Step(STEP_FACE));
//        stepMap.put(STEP_TONGUE_TOP, new Step(STEP_TONGUE_TOP));
//        stepMap.put(STEP_TONGUE_BOTTOM, new Step(STEP_TONGUE_BOTTOM));
        int size = Constant1.TYPES.size();
        for (int i = 0; i < size; i++) {
            int typeId = Constant1.TYPES.keyAt(i);
            stepMap.put(typeId,new Step(typeId));
        }
    }

    private int typeId;
    private String tipText;
    private String title;
    private int dashedResID;
    private int tipResID;
    private String fileName;
    private String photoPath;

    public Step(int typeId) {

        this.typeId = typeId;
        switch (this.typeId) {
            case Constant1.FACE:
                dashedResID = R.drawable.xuxian_mian;
                tipResID = 0;
                fileName = Constant.FILE_NAME_FACE;
                tipText = "拍照在虚线内，识别更精准";
                title = "面诊";
                break;
            case Constant1.TONGUE_TOP:
                dashedResID = R.drawable.xuxian_she;
                tipResID = R.drawable.tip_tongue_top;
                fileName = Constant.FILE_NAME_TONGUE_TOP;
                tipText = "正对白光，张大嘴巴，放松伸出舌头";
                title = "舌诊";
                break;
            case Constant1.TONGUE_BOTTOM:
                dashedResID = R.drawable.xuxian_shedi;
                tipResID = R.drawable.tip_tongue_bottom;
                fileName = Constant.FILE_NAME_TONGUE_BOTTOM;
                tipText = "正对白光，舌尖顶上颚，左右处于居中位置";
                title = "舌诊";
                break;
            default:
        }
    }

    protected Step(Parcel in) {
        typeId = in.readInt();
        tipText = in.readString();
        title = in.readString();
        dashedResID = in.readInt();
        tipResID = in.readInt();
        fileName = in.readString();
        photoPath = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getDashedResID() {
        return dashedResID;
    }

    public String getFileName() {
        return fileName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public int getTipResID() {
        return tipResID;
    }

    public void setTipResID(int tipResID) {
        this.tipResID = tipResID;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(typeId);
        dest.writeString(tipText);
        dest.writeString(title);
        dest.writeInt(dashedResID);
        dest.writeInt(tipResID);
        dest.writeString(fileName);
        dest.writeString(photoPath);
    }
}
