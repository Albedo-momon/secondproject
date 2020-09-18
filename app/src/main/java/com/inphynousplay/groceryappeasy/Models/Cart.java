package com.inphynousplay.groceryappeasy.Models;

public class Cart {
    private  String pid,pname,price,discount,quantity,unit;

    public Cart() {
    }

    public Cart(String pid, String pname, String price, String discount, String quantity, String unit) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
