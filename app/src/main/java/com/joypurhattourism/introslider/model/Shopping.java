package com.joypurhattourism.introslider.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Shopping implements Parcelable {
    public static final Creator<Shopping> CREATOR = new Creator<Shopping>() {
        public Shopping createFromParcel(Parcel in) {
            return new Shopping(in);
        }

        public Shopping[] newArray(int size) {
            return new Shopping[size];
        }
    };
    private String addressBN;
    private String addressEN;
    private String brandsBN;
    private String brandsEN;
    private double distance;
    private String districtBN;
    private String districtEN;
    private String hoursBN;
    private String hoursEN;
    private int id;
    private double latitude;
    private double longitude;
    private String nameBN;
    private String nameEN;
    private String photo;
    private String subDistrictBN;
    private String subDistrictEN;
    private String typeBN;
    private String typeEN;

    public Shopping(int id, String nameEN, String nameBN, String addressEN, String addressBN, String districtEN, String districtBN, String subDistrictEN, String subDistrictBN, String hoursEN, String hoursBN, String brandsEN, String brandsBN, String typeEN, String typeBN, double latitude, double longitude, double distance, String photo) {
        this.id = id;
        this.nameEN = nameEN;
        this.nameBN = nameBN;
        this.addressEN = addressEN;
        this.addressBN = addressBN;
        this.districtEN = districtEN;
        this.districtBN = districtBN;
        this.subDistrictEN = subDistrictEN;
        this.subDistrictBN = subDistrictBN;
        this.hoursEN = hoursEN;
        this.hoursBN = hoursBN;
        this.brandsEN = brandsEN;
        this.brandsBN = brandsBN;
        this.typeEN = typeEN;
        this.typeBN = typeBN;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.photo = photo;
    }

    public Shopping(Parcel in) {
        this.id = in.readInt();
        this.nameEN = in.readString();
        this.nameBN = in.readString();
        this.addressEN = in.readString();
        this.addressBN = in.readString();
        this.districtEN = in.readString();
        this.districtBN = in.readString();
        this.subDistrictEN = in.readString();
        this.subDistrictBN = in.readString();
        this.hoursEN = in.readString();
        this.hoursBN = in.readString();
        this.brandsEN = in.readString();
        this.brandsBN = in.readString();
        this.typeEN = in.readString();
        this.typeBN = in.readString();
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

    public String getHoursEN() {
        return this.hoursEN;
    }

    public void setHoursEN(String hoursEN) {
        this.hoursEN = hoursEN;
    }

    public String getHoursBN() {
        return this.hoursBN;
    }

    public void setHoursBN(String hoursBN) {
        this.hoursBN = hoursBN;
    }

    public String getBrandsEN() {
        return this.brandsEN;
    }

    public void setBrandsEN(String brandsEN) {
        this.brandsEN = brandsEN;
    }

    public String getBrandsBN() {
        return this.brandsBN;
    }

    public void setBrandsBN(String brandsBN) {
        this.brandsBN = brandsBN;
    }

    public String getTypeEN() {
        return this.typeEN;
    }

    public void setTypeEN(String typeEN) {
        this.typeEN = typeEN;
    }

    public String getTypeBN() {
        return this.typeBN;
    }

    public void setTypeBN(String typeBN) {
        this.typeBN = typeBN;
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
        dest.writeString(this.hoursEN);
        dest.writeString(this.hoursBN);
        dest.writeString(this.brandsEN);
        dest.writeString(this.brandsBN);
        dest.writeString(this.typeEN);
        dest.writeString(this.typeBN);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.distance);
        dest.writeString(this.photo);
    }
}
