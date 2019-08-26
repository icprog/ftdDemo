package com.william.ftd_base;

import android.os.Parcel;
import android.os.Parcelable;

public class FtdResult implements Parcelable {

    private String stepId;
    private String path;
    private String imgRes;

    public FtdResult(String stepId, String path) {
        this.stepId = stepId;
        this.path = path;
    }

    protected FtdResult(Parcel in) {
        stepId = in.readString();
        path = in.readString();
    }

    public static final Creator<FtdResult> CREATOR = new Creator<FtdResult>() {
        @Override
        public FtdResult createFromParcel(Parcel in) {
            return new FtdResult(in);
        }

        @Override
        public FtdResult[] newArray(int size) {
            return new FtdResult[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stepId);
        dest.writeString(path);
    }
}
