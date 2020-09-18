package com.inphynousplay.groceryappeasy.Models;

public class FruitProduct {
    String image;
    String pname;
    String unit;
    String price;
    String disc;
    String pid;

    public FruitProduct() {
    }

    public FruitProduct(String image, String pname, String unit, String price, String disc, String pid) {
        this.image = image;
        this.pname = pname;
        this.unit = unit;
        this.price = price;
        this.disc = disc;
        this.pid = pid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
