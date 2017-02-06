package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Marker implements Parcelable {
    public static final Creator<Marker> CREATOR = new Creator<Marker>() {
        public Marker createFromParcel(Parcel in) {
            return new Marker(in);
        }

        public Marker[] newArray(int size) {
            return new Marker[size];
        }
    };
    private String description;
    private int id;
    private double latitude;
    private double longitude;
    private String markerCategory;
    private String title;

    public Marker(int id, double latitude, double longitude, String title, String description, String markerCategory) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.markerCategory = markerCategory;
    }

    public Marker(Parcel in) {
        this.id = in.readInt();
        this.latitude = in.readDouble();
        this.latitude = in.readDouble();
        this.title = in.readString();
        this.description = in.readString();
        this.markerCategory = in.readString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarkerCategory() {
        return this.markerCategory;
    }

    public void setMarkerCategory(String markerCategory) {
        this.markerCategory = markerCategory;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.markerCategory);
    }
}
