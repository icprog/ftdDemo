package com.william.ftd_base.constant;


import android.os.Parcel;
import android.os.Parcelable;

import com.william.ftd_base.R;

public class Step implements Parcelable {

    private String stepId;
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
                break;
            case Constant.STEP_TONGUE_TOP:
                dashedResID = R.drawable.xuxian_she;
                tipResID = R.drawable.tip_tongue_top;
                fileName = Constant.FILE_NAME_TONGUE_TOP;
                break;
            case Constant.STEP_TONGUE_BOTTOM:
                dashedResID = R.drawable.xuxian_shedi;
                tipResID = R.drawable.tip_tongue_bottom;
                fileName = Constant.FILE_NAME_TONGUE_BOTTOM;
                break;
            default:
        }
    }

    protected Step(Parcel in) {
        stepId = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stepId);
        dest.writeInt(dashedResID);
        dest.writeInt(tipResID);
        dest.writeString(fileName);
        dest.writeString(photoPath);
    }
}
