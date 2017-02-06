package com.joypurhattourism.introslider.model;

public class SpinnerNavItem {
    private int id;
    private String title;

    public SpinnerNavItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }
}
