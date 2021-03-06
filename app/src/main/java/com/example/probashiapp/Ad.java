package com.example.probashiapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Ad implements Parcelable {
    String title, country, vacancy, job_type, job_security, visa_grade, basic_pay, work_hour, description, package_price, agency_id, ad_id, imageurl;
    Long time;
    ArrayList<String> applicants ;
    String adStatus;

    public Ad(String title, String country, String vacancy, String job_type, String job_security, String visa_grade, String basic_pay, String work_hour, String description, String package_price, String agency_id, String ad_id, String imageurl, Long time, ArrayList<String> applicants, String adStatus) {
        this.title = title;
        this.country = country;
        this.vacancy = vacancy;
        this.job_type = job_type;
        this.job_security = job_security;
        this.visa_grade = visa_grade;
        this.basic_pay = basic_pay;
        this.work_hour = work_hour;
        this.description = description;
        this.package_price = package_price;
        this.agency_id = agency_id;
        this.ad_id = ad_id;
        this.imageurl = imageurl;
        this.time = time;
        this.applicants = applicants;
        this.adStatus = adStatus;
    }

    public Ad() {
    }

    protected Ad(Parcel in) {
        title = in.readString();
        country = in.readString();
        vacancy = in.readString();
        job_type = in.readString();
        job_security = in.readString();
        visa_grade = in.readString();
        basic_pay = in.readString();
        work_hour = in.readString();
        description = in.readString();
        package_price = in.readString();
        agency_id = in.readString();
        ad_id = in.readString();
        imageurl = in.readString();
        if (in.readByte() == 0) {
            time = null;
        } else {
            time = in.readLong();
        }
        applicants = in.createStringArrayList();
        adStatus = in.readString();
    }

    public static final Creator<Ad> CREATOR = new Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel in) {
            return new Ad(in);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getJob_security() {
        return job_security;
    }

    public void setJob_security(String job_security) {
        this.job_security = job_security;
    }

    public String getVisa_grade() {
        return visa_grade;
    }

    public void setVisa_grade(String visa_grade) {
        this.visa_grade = visa_grade;
    }

    public String getBasic_pay() {
        return basic_pay;
    }

    public void setBasic_pay(String basic_pay) {
        this.basic_pay = basic_pay;
    }

    public String getWork_hour() {
        return work_hour;
    }

    public void setWork_hour(String work_hour) {
        this.work_hour = work_hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public ArrayList<String> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<String> applicants) {
        this.applicants = applicants;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(country);
        parcel.writeString(vacancy);
        parcel.writeString(job_type);
        parcel.writeString(job_security);
        parcel.writeString(visa_grade);
        parcel.writeString(basic_pay);
        parcel.writeString(work_hour);
        parcel.writeString(description);
        parcel.writeString(package_price);
        parcel.writeString(agency_id);
        parcel.writeString(ad_id);
        parcel.writeString(imageurl);
        if (time == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(time);
        }
        parcel.writeStringList(applicants);
        parcel.writeString(adStatus);
    }
}
