package com.thecodealchemist.spring_boot_project.model;

import java.sql.Timestamp;

public class CounsellingSession {
    private int sessionId;
    private int counsellorId;
    private int studentId;
    private int serviceId;
    private boolean isApproved;
    private CounsellingMode counsellingMode;
    private Timestamp finalTime;

    public enum CounsellingMode {
        Online,
        Offline
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(int counsellorId) {
        this.counsellorId = counsellorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
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