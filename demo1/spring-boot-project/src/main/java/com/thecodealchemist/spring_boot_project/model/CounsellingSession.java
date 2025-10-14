package com.thecodealchemist.spring_boot_project.model;

import java.sql.Timestamp;

public class CounsellingSession {

    private int counsellorId;
    private int serviceId;
    private int studentId;
    private boolean isApproved;
    private String timeOptions;
    private CounsellingMode counsellingMode;
    private Timestamp finalTime;

    public enum CounsellingMode {
        Online, Offline
    }

    // Getters and Setters

    public int getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(int counsellorId) {
        this.counsellorId = counsellorId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getTimeOptions() {
        return timeOptions;
    }

    public void setTimeOptions(String timeOptions) {
        this.timeOptions = timeOptions;
    }

    public CounsellingMode getCounsellingMode() {
        return counsellingMode;
    }

    public void setCounsellingMode(CounsellingMode counsellingMode) {
        this.counsellingMode = counsellingMode;
    }

    public Timestamp getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Timestamp finalTime) {
        this.finalTime = finalTime;
    }
}