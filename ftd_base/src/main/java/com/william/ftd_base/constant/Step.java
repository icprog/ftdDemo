package com.william.ftd_base.constant;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;

import com.william.ftd_base.R;

public class Step implements Parcelable {

//    public static Step[] steps = new Step[3];
    public static ArrayMap<String,Step> stepMap = new ArrayMap<>();
    static {
        stepMap.put(Constant.STEP_FACE,null);
        stepMap.put(Constant.STEP_TONGUE_TOP,null);
        stepMap.put(Constant.STEP_TONGUE_BOTTOM,null);
    }

    private String stepId;
    private String tipText;
    private String title;
    private int dashedResID;
    private int tipResID;
    private String fileName;
    private String photoPath;

    public Step(@Constant.StepId String stepId) {

        this.stepId = stepId;
        switch (stepId) {
            case Constant.STEP_FACE:
                dashedResID = R.drawable.xuxian_mian;
                tipResID = 0;
                fileName = Constant.FILE_NAME_FACE;
                tipText = "拍照在虚线内，识别更精准";
                title = "面诊";
                break;
            case Constant.STEP_TONGUE_TOP:
                dashedResID = R.drawable.xuxian_she;
                tipResID = R.drawable.tip_tongue_top;
                fileName = Constant.FILE_NAME_TONGUE_TOP;
                tipText = "正对白光，张大嘴巴，放松伸出舌头";
                title = "舌诊";
                break;
            case Constant.STEP_TONGUE_BOTTOM:
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
        stepId = in.readString();
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

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
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
        dest.writeString(stepId);
        dest.writeString(tipText);
        dest.writeString(title);
        dest.writeInt(dashedResID);
        dest.writeInt(tipResID);
        dest.writeString(fileName);
        dest.writeString(photoPath);
    }
}
