package com.example.probashiapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Application implements Parcelable {
    String ad_id, agent_id, applicant_id, agency_id,application_id;
    Agents agent;
    Ad ad;
    Date apply_time;
    boolean contacted;

    public Application() {
    }

    public Application(String ad_id, String agent_id, String applicant_id, String agency_id, Agents agent, Ad ad, Date apply_time, boolean contacted) {
        this.ad_id = ad_id;
        this.agent_id = agent_id;
        this.applicant_id = applicant_id;
        this.agency_id = agency_id;
        this.agent = agent;
        this.ad = ad;
        this.apply_time = apply_time;
        this.contacted = contacted;
    }

    protected Application(Parcel in) {
        ad_id = in.readString();
        agent_id = in.readString();
        applicant_id = in.readString();
        agency_id = in.readString();
        agent = in.readParcelable(Agents.class.getClassLoader());
        ad = in.readParcelable(Ad.class.getClassLoader());
        contacted = in.readByte() != 0;
    }

    public static final Creator<Application> CREATOR = new Creator<Application>() {
        @Override
        public Application createFromParcel(Parcel in) {
            return new Application(in);
        }

        @Override
        public Application[] newArray(int size) {
            return new Application[size];
        }
    };

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public boolean isContacted() {
        return contacted;
    }

    public void setContacted(boolean contacted) {
        this.contacted = contacted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ad_id);
        parcel.writeString(agent_id);
        parcel.writeString(applicant_id);
        parcel.writeString(agency_id);
        parcel.writeParcelable(agent, i);
        parcel.writeParcelable(ad, i);
        parcel.writeByte((byte) (contacted ? 1 : 0));
    }
}
