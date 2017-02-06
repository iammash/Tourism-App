package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Place implements Parcelable {
    public static final Creator<Place> CREATOR = new Creator<Place>() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
    private String address;
    private String contact;
    private String description;
    private double distance;
    private int id;
    private int image;
    private double latitude;
    private double longitude;
    private String name;
    private String rating;
    private String visitTime;

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Place(int id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Place(int id, String name, double distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public Place(int id, String name, String address, String rating, int image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.image = image;
    }

    public Place(int id, String name, String description, double distance, int image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.distance = distance;
        this.image = image;
    }

    public Place(int id, String name, String address, String cost, String rating, int image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = cost;
        this.rating = rating;
        this.image = image;
    }

    public Place(int id, String name, String description, String address, String contact, int image, double latitude, double longitude, double distance, String visitTime, String rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.visitTime = visitTime;
        this.rating = rating;
    }

    public Place(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.address = in.readString();
        this.contact = in.readString();
        this.image = in.readInt();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.distance = in.readDouble();
        this.visitTime = in.readString();
        this.rating = in.readString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.address);
        dest.writeString(this.contact);
        dest.writeInt(this.image);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        dest.writeString(this.visitTime);
        dest.writeString(this.rating);
    }
}
