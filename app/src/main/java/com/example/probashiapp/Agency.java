package com.example.probashiapp;

public class Agency {
    String name,phone,address,area,city,regno;

    public Agency() {
    }

    public Agency(String name, String phone, String address, String area, String city, String regno) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.city = city;
        this.regno = regno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }
}
