package com.inphynousplay.groceryappeasy.Models;

public class BannerModel {

    private String carname;
    private String ImageUrl;

    public BannerModel(String carname, String imageUrl) {
        this.carname = carname;
        ImageUrl = imageUrl;
    }

    public BannerModel() {
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
