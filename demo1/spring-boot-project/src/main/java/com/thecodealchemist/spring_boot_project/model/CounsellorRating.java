package com.thecodealchemist.spring_boot_project.model;

import java.sql.Timestamp;

public class CounsellorRating {

    private int counsellorId;
    private int studentId;
    private int rating;
    private String feedback;
    private Timestamp ratedAt;
    private Counsellor counsellor;
    private Student student;

    // Getters and Setters

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(Timestamp ratedAt) {
        this.ratedAt = ratedAt;
    }

    public Counsellor getCounsellor() {
        return counsellor;
    }

    public void setCounsellor(Counsellor counsellor) {
        this.counsellor = counsellor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}