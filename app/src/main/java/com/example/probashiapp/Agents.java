package com.example.probashiapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Agents implements Parcelable {
    public static final Creator<Agents> CREATOR = new Creator<Agents>() {
        @Override
        public Agents createFromParcel(Parcel in) {
            return new Agents(in);
        }

        @Override
        public Agents[] newArray(int size) {
            return new Agents[size];
        }
    };

    public Agents() {
    }

    String name, phone, email, address, city, nid, profilePhoto_url, nidPhoto_url, passportPhoto_url;

    public Agents(String name, String phone, String email, String address, String city, String nid, String profilePhoto_url, String nidPhoto_url, String passportPhoto_url) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.nid = nid;
        this.profilePhoto_url = profilePhoto_url;
        this.nidPhoto_url = nidPhoto_url;
        this.passportPhoto_url = passportPhoto_url;
    }

    protected Agents(Parcel in) {
        name = in.readString();
        phone = in.readString();
        email = in.readString();
        address = in.readString();
        city = in.readString();
        nid = in.readString();
        profilePhoto_url = in.readString();
        nidPhoto_url = in.readString();
        passportPhoto_url = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(email);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(nid);
        parcel.writeString(profilePhoto_url);
        parcel.writeString(nidPhoto_url);
        parcel.writeString(passportPhoto_url);
    }
}
