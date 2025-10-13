package com.thecodealchemist.spring_boot_project.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Counsellor {

    public enum Specialization {
        Academics,
        Substance_Addiction,
        Stress_Anxiety,
        Grief_Loss,
        Personal_Relationships
    }

    private int counsellorId;
    private Specialization specialization;
    private int noOfStudentsCounselled;
    private String selfDescription;
    private BigDecimal rating;
    private Timestamp joinedAt;
    private Student student;
    private List<CounsellorAvailability> availableTimes;

    // Getters and Setters

    public int getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(int counsellorId) {
        this.counsellorId = counsellorId;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getNoOfStudentsCounselled() {
        return noOfStudentsCounselled;
    }

    public void setNoOfStudentsCounselled(int noOfStudentsCounselled) {
        this.noOfStudentsCounselled = noOfStudentsCounselled;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Timestamp getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Timestamp joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<CounsellorAvailability> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<CounsellorAvailability> availableTimes) {
        this.availableTimes = availableTimes;
    }
}