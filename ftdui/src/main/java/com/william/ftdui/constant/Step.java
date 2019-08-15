package com.william.ftdui.constant;

import android.os.Parcel;
import android.os.Parcelable;

import com.william.ftd_core.constant.ServiceApi;
import com.william.ftdui.R;


public class Step implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    public Step(Parcel in) {
        type = in.readInt();
        drawableId = in.readInt();
        fileName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(drawableId);
        dest.writeString(fileName);
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
}
