package com.example.android_team_project.models;

public class Location {

    // DATA MEMBERS
    private String name;
    private String desc;
    private String price;
    private String address;
    private String review;
    private String imageURL;

    public Location() {
        name = "name";
        desc = "desc";
        price = "$";
        review = "review";
        address = "address";
        imageURL = "";
    }

    public Location(String name, String desc, String address, String price, String review, String imageURL) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.review = review;
        this.address = address;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
