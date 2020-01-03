package com.lk.ftdui.activity.param;

import android.os.Parcel;
import android.os.Parcelable;

public class DoctorInfo implements Parcelable {

    private String doctorID;
    private String doctorMemberCode;

    protected DoctorInfo(Parcel in) {
        doctorID = in.readString();
        doctorMemberCode = in.readString();
    }

    public static final Creator<DoctorInfo> CREATOR = new Creator<DoctorInfo>() {
        @Override
        public DoctorInfo createFromParcel(Parcel in) {
            return new DoctorInfo(in);
        }

        @Override
        public DoctorInfo[] newArray(int size) {
            return new DoctorInfo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctorID);
        dest.writeString(doctorMemberCode);
    }

    public DoctorInfo(String doctorID, String doctorMemberCode) {
        this.doctorID = doctorID;
        this.doctorMemberCode = doctorMemberCode;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorMemberCode() {
        return doctorMemberCode;
    }

    public void setDoctorMemberCode(String doctorMemberCode) {
        this.doctorMemberCode = doctorMemberCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
