package com.thecodealchemist.spring_boot_project.model;

import java.sql.Timestamp;

public class Counsellor {
    private int counsellorId;
    private Specialization specialization;
    private int noOfStudentsCounselled;
    private String selfDescription;
    private Timestamp joinedAt;
    private Student student;

    public enum Specialization {
        Academics,
        Substance_Addiction,
        Stress_Anxiety,
        Grief_Loss,
        Personal_Relationships
    }

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
}