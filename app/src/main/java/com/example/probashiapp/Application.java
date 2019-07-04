package com.example.probashiapp;

import java.util.Date;

public class Application {
    String ad_id, agent_id, applicant_id, agency_id;
    Date apply_time;

    public Application(String ad_id, String agent_id, String applicant_id, String agency_id, Date apply_time) {
        this.ad_id = ad_id;
        this.agent_id = agent_id;
        this.applicant_id = applicant_id;
        this.agency_id = agency_id;
        this.apply_time = apply_time;
    }

    public Application() {
    }

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

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }
}
