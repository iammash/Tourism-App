package com.joypurhattourism.introslider.model;

public class Markera {
    private String description;
    private int id;
    private double latitude;
    private double longitude;
    private String markerCategory;
    private String title;

    public Markera(int id, double latitude, double longitude, String title, String description, String markerCategory) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.markerCategory = markerCategory;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
