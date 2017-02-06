package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Attraction implements Parcelable {
    public static final Creator<Attraction> CREATOR = new Creator<Attraction>() {
        public Attraction createFromParcel(Parcel in) {
            return new Attraction(in);
        }

        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };
    private String aboutBN;
    private String aboutEN;
    private double distance;
    private int id;
    private double latitude;
    private double longitude;
    private String nameBN;
    private String nameEN;
    private String photo;
    private int touristSpotId;
    private String travelBN;
    private String travelEN;

    public Attraction(int id, int touristSpotId, String nameEN, String nameBN, String aboutEN, String aboutBN, String travelEN, String travelBN, double latitude, double longitude, double distance, String photo) {
        this.id = id;
        this.touristSpotId = touristSpotId;
        this.nameEN = nameEN;
        this.nameBN = nameBN;
        this.aboutEN = aboutEN;
        this.aboutBN = aboutBN;
        this.travelEN = travelEN;
        this.travelBN = travelBN;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.photo = photo;
    }

    public Attraction(Parcel in) {
        this.id = in.readInt();
        this.touristSpotId = in.readInt();
        this.nameEN = in.readString();
        this.nameBN = in.readString();
        this.aboutEN = in.readString();
        this.aboutBN = in.readString();
        this.travelEN = in.readString();
        this.travelBN = in.readString();
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

    public int getTouristSpotId() {
        return this.touristSpotId;
    }

    public void setTouristSpotId(int touristSpotId) {
        this.touristSpotId = touristSpotId;
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
        dest.writeInt(this.touristSpotId);
        dest.writeString(this.nameEN);
        dest.writeString(this.nameBN);
        dest.writeString(this.aboutEN);
        dest.writeString(this.aboutBN);
        dest.writeString(this.travelEN);
        dest.writeString(this.travelBN);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        dest.writeString(this.photo);
    }
}
