package com.thecodealchemist.spring_boot_project.model;

import java.util.List;

public class CounsellorViewDTO {
    private int counsellorId;
    private String specialization;
    private String firstName;
    private String lastName;
    private String selfDescription;
    private List<String> availableTimes;

    public CounsellorViewDTO(int counsellorId, String specialization, String firstName, String lastName, String selfDescription) {
        this.counsellorId = counsellorId;
        this.specialization = specialization;
        this.firstName = firstName;
        this.lastName = lastName;
        this.selfDescription = selfDescription;
    }

    public int getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(int counsellorId) {
        this.counsellorId = counsellorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }
}