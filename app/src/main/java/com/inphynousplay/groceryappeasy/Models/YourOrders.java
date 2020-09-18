package com.inphynousplay.groceryappeasy.Models;

public class YourOrders {
    private String address,name,date,time,totalAmount,delievery_time,state,phone,pincode;

    public YourOrders() {
    }

    public YourOrders(String address, String name, String date, String time, String totalAmount, String delievery_time, String state, String phone, String pincode) {
        this.address = address;
        this.name = name;
        this.date = date;
        this.time = time;
        this.totalAmount = totalAmount;
        this.delievery_time = delievery_time;
        this.state = state;
        this.phone = phone;
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDelievery_time() {
        return delievery_time;
    }

    public void setDelievery_time(String delievery_time) {
        this.delievery_time = delievery_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
