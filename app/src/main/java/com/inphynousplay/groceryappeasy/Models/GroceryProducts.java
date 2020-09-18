package com.inphynousplay.groceryappeasy.Models;

public class GroceryProducts {
    private String pname,unit,price,image,disc,pid,status,mrp,savings,type;


    public GroceryProducts() {
    }

    public GroceryProducts(String pname, String unit, String price, String image, String disc, String pid, String status, String mrp, String savings, String type) {
        this.pname = pname;
        this.unit = unit;
        this.price = price;
        this.image = image;
        this.disc = disc;
        this.pid = pid;
        this.status = status;
        this.mrp = mrp;
        this.savings = savings;
        this.type = type;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

