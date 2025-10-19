package com.thecodealchemist.spring_boot_project.model;

import java.sql.Timestamp;

public class SessionViewDTO {
    private String counsellorName;
    private String specialization;
    private Timestamp sessionTime;

    public SessionViewDTO(String counsellorName, String specialization, Timestamp sessionTime) {
        this.counsellorName = counsellorName;
        this.specialization = specialization;
        this.sessionTime = sessionTime;
    }

    public String getCounsellorName() {
        return counsellorName;
    }

    public void setCounsellorName(String counsellorName) {
        this.counsellorName = counsellorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Timestamp getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Timestamp sessionTime) {
        this.sessionTime = sessionTime;
    }
}