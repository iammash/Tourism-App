package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Police implements Parcelable {
    public static final Creator<Police> CREATOR = new Creator<Police>() {
        public Police createFromParcel(Parcel in) {
            return new Police(in);
        }

        public Police[] newArray(int size) {
            return new Police[size];
        }
    };
    private String designationBN;
    private String designationEN;
    private String email;
    private int id;
    private String mobileBN;
    private String mobileEN;
    private String phoneBN;
    private String phoneEN;
    private int policeStationId;

    public Police(int id, int policeStationId, String designationEN, String designationBN, String mobileEN, String mobileBN, String phoneEN, String phoneBN, String email) {
        this.id = id;
        this.policeStationId = policeStationId;
        this.designationEN = designationEN;
        this.designationBN = designationBN;
        this.mobileEN = mobileEN;
        this.mobileBN = mobileBN;
        this.phoneEN = phoneEN;
        this.phoneBN = phoneBN;
        this.email = email;
    }

    public Police(Parcel in) {
        this.id = in.readInt();
        this.policeStationId = in.readInt();
        this.designationEN = in.readString();
        this.designationBN = in.readString();
        this.mobileEN = in.readString();
        this.mobileBN = in.readString();
        this.phoneEN = in.readString();
        this.phoneBN = in.readString();
        this.email = in.readString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoliceStationId() {
        return this.policeStationId;
    }

    public void setPoliceStationId(int policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getDesignationEN() {
        return this.designationEN;
    }

    public void setDesignationEN(String designationEN) {
        this.designationEN = designationEN;
    }

    public String getDesignationBN() {
        return this.designationBN;
    }

    public void setDesignationBN(String designationBN) {
        this.designationBN = designationBN;
    }

    public String getMobileEN() {
        return this.mobileEN;
    }

    public void setMobileEN(String mobileEN) {
        this.mobileEN = mobileEN;
    }

    public String getMobileBN() {
        return this.mobileBN;
    }

    public void setMobileBN(String mobileBN) {
        this.mobileBN = mobileBN;
    }

    public String getPhoneEN() {
        return this.phoneEN;
    }

    public void setPhoneEN(String phoneEN) {
        this.phoneEN = phoneEN;
    }

    public String getPhoneBN() {
        return this.phoneBN;
    }

    public void setPhoneBN(String phoneBN) {
        this.phoneBN = phoneBN;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.policeStationId);
        dest.writeString(this.designationEN);
        dest.writeString(this.designationBN);
        dest.writeString(this.mobileEN);
        dest.writeString(this.mobileBN);
        dest.writeString(this.phoneEN);
        dest.writeString(this.phoneBN);
        dest.writeString(this.email);
    }
}
