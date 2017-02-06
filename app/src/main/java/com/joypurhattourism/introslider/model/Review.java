package com.joypurhattourism.introslider.model;

public class Review {
    private String comments;
    private int rating;
    private String timeStamp;
    private String userEmail;
    private String userFirstName;
    private String userLastName;

    public Review(String firstName, String lastName, String email, String comments, int rating, String timeStamp) {
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userEmail = email;
        this.comments = comments;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }

    public String getUserFullName() {
        return this.userFirstName.concat(" ").concat(this.userLastName);
    }

    public String getUserFirstName() {
        return this.userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return this.userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
