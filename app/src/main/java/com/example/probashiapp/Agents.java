package com.example.probashiapp;

public class Agents {
    String name,password,retypepassword,phone,email,address,city,nid,profilePhoto_url,nidPhoto_url,passportPhoto_url;

    public Agents() {
    }

    public Agents(String name, String password, String retypepassword, String phone, String email, String address, String city, String nid, String profilePhoto_url, String nidPhoto_url, String passportPhoto_url) {
        this.name = name;
        this.password = password;
        this.retypepassword = retypepassword;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.nid = nid;
        this.profilePhoto_url = profilePhoto_url;
        this.nidPhoto_url = nidPhoto_url;
        this.passportPhoto_url = passportPhoto_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getProfilePhoto_url() {
        return profilePhoto_url;
    }

    public void setProfilePhoto_url(String profilePhoto_url) {
        this.profilePhoto_url = profilePhoto_url;
    }

    public String getNidPhoto_url() {
        return nidPhoto_url;
    }

    public void setNidPhoto_url(String nidPhoto_url) {
        this.nidPhoto_url = nidPhoto_url;
    }

    public String getPassportPhoto_url() {
        return passportPhoto_url;
    }

    public void setPassportPhoto_url(String passportPhoto_url) {
        this.passportPhoto_url = passportPhoto_url;
    }
}
