package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TouristSpot implements Parcelable {
    public static final Creator<TouristSpot> CREATOR = new Creator<TouristSpot>() {
        public TouristSpot createFromParcel(Parcel in) {
            return new TouristSpot(in);
        }

        public TouristSpot[] newArray(int size) {
            return new TouristSpot[size];
        }
    };
    private String aboutBN;
    private String aboutEN;
    private String addressBN;
    private String addressEN;
    private String bestTimeToVisitBN;
    private String bestTimeToVisitEN;
    private double distance;
    private String distanceFromBarisalBN;
    private String distanceFromBarisalEN;
    private String districtBN;
    private String districtEN;
    private int id;
    private String idealStayBN;
    private String idealStayEN;
    private double latitude;
    private double longitude;
    private String nameBN;
    private String nameEN;
    private String photo;
    private String subDistrictBN;
    private String subDistrictEN;
    private String travelBN;
    private String travelEN;

    public TouristSpot(int id, String nameEN, String nameBN, String addressEN, String addressBN, String districtEN, String districtBN, String subDistrictEN, String subDistrictBN, String aboutEN, String aboutBN, String travelEN, String travelBN, String distanceFromBarisalEN, String distanceFromBarisalBN, String bestTimeToVisitEN, String bestTimeToVisitBN, String idealStayEN, String idealStayBN, double latitude, double longitude, String photo) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameBN = nameBN;
        this.addressEN = addressEN;
        this.addressBN = addressBN;
        this.districtEN = districtEN;
        this.districtBN = districtBN;
        this.subDistrictEN = subDistrictEN;
        this.subDistrictBN = subDistrictBN;
        this.aboutEN = aboutEN;
        this.aboutBN = aboutBN;
        this.travelEN = travelEN;
        this.travelBN = travelBN;
        this.distanceFromBarisalEN = distanceFromBarisalEN;
        this.distanceFromBarisalBN = distanceFromBarisalBN;
        this.bestTimeToVisitEN = bestTimeToVisitEN;
        this.bestTimeToVisitBN = bestTimeToVisitBN;
        this.idealStayEN = idealStayEN;
        this.idealStayBN = idealStayBN;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
    }

    public TouristSpot(int id, String nameEN, String nameBN, String addressEN, String addressBN, String districtEN, String districtBN, String subDistrictEN, String subDistrictBN, String aboutEN, String aboutBN, String travelEN, String travelBN, String distanceFromBarisalEN, String distanceFromBarisalBN, String bestTimeToVisitEN, String bestTimeToVisitBN, String idealStayEN, String idealStayBN, double latitude, double longitude, double distance, String photo) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameBN = nameBN;
        this.addressEN = addressEN;
        this.addressBN = addressBN;
        this.districtEN = districtEN;
        this.districtBN = districtBN;
        this.subDistrictEN = subDistrictEN;
        this.subDistrictBN = subDistrictBN;
        this.aboutEN = aboutEN;
        this.aboutBN = aboutBN;
        this.travelEN = travelEN;
        this.travelBN = travelBN;
        this.distanceFromBarisalEN = distanceFromBarisalEN;
        this.distanceFromBarisalBN = distanceFromBarisalBN;
        this.bestTimeToVisitEN = bestTimeToVisitEN;
        this.bestTimeToVisitBN = bestTimeToVisitBN;
        this.idealStayEN = idealStayEN;
        this.idealStayBN = idealStayBN;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.photo = photo;
    }

    public TouristSpot(Parcel in) {
        this.id = in.readInt();
        this.nameEN = in.readString();
        this.nameBN = in.readString();
        this.addressEN = in.readString();
        this.addressBN = in.readString();
        this.districtEN = in.readString();
        this.districtBN = in.readString();
        this.subDistrictEN = in.readString();
        this.subDistrictBN = in.readString();
        this.aboutEN = in.readString();
        this.aboutBN = in.readString();
        this.travelEN = in.readString();
        this.travelBN = in.readString();
        this.distanceFromBarisalEN = in.readString();
        this.distanceFromBarisalBN = in.readString();
        this.bestTimeToVisitEN = in.readString();
        this.bestTimeToVisitBN = in.readString();
        this.idealStayEN = in.readString();
        this.idealStayBN = in.readString();
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

    public String getAboutEN() {
        return this.aboutEN;
    }

    public void setAboutEN(String aboutEN) {
        this.aboutEN = aboutEN;
    }

    public String getAboutBN() {
        return this.aboutBN;
    }

    public void setAboutBN(String aboutBN) {
        this.aboutBN = aboutBN;
    }

    public String getTravelEN() {
        return this.travelEN;
    }

    public void setTravelEN(String travelEN) {
        this.travelEN = travelEN;
    }

    public String getTravelBN() {
        return this.travelBN;
    }

    public void setTravelBN(String travelBN) {
        this.travelBN = travelBN;
    }

    public String getDistanceFromBarisalEN() {
        return this.distanceFromBarisalEN;
    }

    public void setDistanceFromBarisalEN(String distanceFromBarisalEN) {
        this.distanceFromBarisalEN = distanceFromBarisalEN;
    }

    public String getDistanceFromBarisalBN() {
        return this.distanceFromBarisalBN;
    }

    public void setDistanceFromBarisalBN(String distanceFromBarisalBN) {
        this.distanceFromBarisalBN = distanceFromBarisalBN;
    }

    public String getBestTimeToVisitEN() {
        return this.bestTimeToVisitEN;
    }

    public void setBestTimeToVisitEN(String bestTimeToVisitEN) {
        this.bestTimeToVisitEN = bestTimeToVisitEN;
    }

    public String getBestTimeToVisitBN() {
        return this.bestTimeToVisitBN;
    }

    public void setBestTimeToVisitBN(String bestTimeToVisitBN) {
        this.bestTimeToVisitBN = bestTimeToVisitBN;
    }

    public String getIdealStayEN() {
        return this.idealStayEN;
    }

    public void setIdealStayEN(String idealStayEN) {
        this.idealStayEN = idealStayEN;
    }

    public String getIdealStayBN() {
        return this.idealStayBN;
    }

    public void setIdealStayBN(String idealStayBN) {
        this.idealStayBN = idealStayBN;
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
        dest.writeString(this.aboutEN);
        dest.writeString(this.aboutBN);
        dest.writeString(this.travelEN);
        dest.writeString(this.travelBN);
        dest.writeString(this.distanceFromBarisalEN);
        dest.writeString(this.distanceFromBarisalBN);
        dest.writeString(this.bestTimeToVisitEN);
        dest.writeString(this.bestTimeToVisitBN);
        dest.writeString(this.idealStayEN);
        dest.writeString(this.idealStayBN);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        dest.writeString(this.photo);
    }
}
