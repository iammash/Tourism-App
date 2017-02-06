package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Hotel implements Parcelable {
    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
    private String addressBN;
    private String addressEN;
    private String costBN;
    private String costEN;
    private double distance;
    private String districtBN;
    private String districtEN;
    private int id;
    private double latitude;
    private double longitude;
    private String mobileBN;
    private String mobileEN;
    private String nameBN;
    private String nameEN;
    private String photo;
    private String subDistrictBN;
    private String subDistrictEN;

    public Hotel(int id, String nameEN, String nameBN, String addressEN, String addressBN, String districtEN, String districtBN, String subDistrictEN, String subDistrictBN, String costEN, String costBN, String mobileEN, String mobileBN, double latitude, double longitude, double distance, String photo) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameBN = nameBN;
        this.addressEN = addressEN;
        this.addressBN = addressBN;
        this.districtEN = districtEN;
        this.districtBN = districtBN;
        this.subDistrictEN = subDistrictEN;
        this.subDistrictBN = subDistrictBN;
        this.costEN = costEN;
        this.costBN = costBN;
        this.mobileEN = mobileEN;
        this.mobileBN = mobileBN;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.photo = photo;
    }

    public Hotel(Parcel in) {
        this.id = in.readInt();
        this.nameEN = in.readString();
        this.nameBN = in.readString();
        this.addressEN = in.readString();
        this.addressBN = in.readString();
        this.districtEN = in.readString();
        this.districtBN = in.readString();
        this.subDistrictEN = in.readString();
        this.subDistrictBN = in.readString();
        this.costEN = in.readString();
        this.costBN = in.readString();
        this.mobileEN = in.readString();
        this.mobileBN = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.distance = in.readDouble();
        this.photo = in.readString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEN() {
        return this.nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameBN() {
        return this.nameBN;
    }

    public void setNameBN(String nameBN) {
        this.nameBN = nameBN;
    }

    public String getAddressEN() {
        return this.addressEN;
    }

    public void setAddressEN(String addressEN) {
        this.addressEN = addressEN;
    }

    public String getAddressBN() {
        return this.addressBN;
    }

    public void setAddressBN(String addressBN) {
        this.addressBN = addressBN;
    }

    public String getDistrictEN() {
        return this.districtEN;
    }

    public void setDistrictEN(String districtEN) {
        this.districtEN = districtEN;
    }

    public String getDistrictBN() {
        return this.districtBN;
    }

    public void setDistrictBN(String districtBN) {
        this.districtBN = districtBN;
    }

    public String getSubDistrictEN() {
        return this.subDistrictEN;
    }

    public void setSubDistrictEN(String subDistrictEN) {
        this.subDistrictEN = subDistrictEN;
    }

    public String getSubDistrictBN() {
        return this.subDistrictBN;
    }

    public void setSubDistrictBN(String subDistrictBN) {
        this.subDistrictBN = subDistrictBN;
    }

    public String getCostEN() {
        return this.costEN;
    }

    public void setCostEN(String costEN) {
        this.costEN = costEN;
    }

    public String getCostBN() {
        return this.costBN;
    }

    public void setCostBN(String costBN) {
        this.costBN = costBN;
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

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nameEN);
        dest.writeString(this.nameBN);
        dest.writeString(this.addressEN);
        dest.writeString(this.addressBN);
        dest.writeString(this.districtEN);
        dest.writeString(this.districtBN);
        dest.writeString(this.subDistrictEN);
        dest.writeString(this.subDistrictBN);
        dest.writeString(this.costEN);
        dest.writeString(this.costBN);
        dest.writeString(this.mobileEN);
        dest.writeString(this.mobileBN);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        dest.writeString(this.photo);
    }
}
