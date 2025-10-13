package com.thecodealchemist.spring_boot_project.service;

import com.thecodealchemist.spring_boot_project.dao.*;
import com.thecodealchemist.spring_boot_project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CounsellingService {

    @Autowired
    private CounsellingSessionRepository sessionRepository;

    @Autowired
    private CounsellorAvailabilityRepository availabilityRepository;

    @Autowired
    private CounsellorRatingRepository ratingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Transactional
    public CounsellingSession bookSession(int studentId, int counsellorId, Time sessionTime) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        Counsellor counsellor = counsellorRepository.findById(counsellorId);
        if (counsellor == null) {
            throw new RuntimeException("Counsellor not found");
        }

        CounsellorAvailability availability = availabilityRepository.findById(counsellorId, sessionTime);

        if (availability == null) {
            throw new RuntimeException("Time slot not available");
        }

        if (availability.isBooked()) {
            throw new RuntimeException("Time slot is already booked");
        }
        availability.setBooked(true);
        availabilityRepository.update(availability);

        CounsellingSession session = new CounsellingSession();
        session.setStudentId(studentId);
        session.setCounsellorId(counsellorId);

        LocalDate today = LocalDate.now();
        LocalTime localSessionTime = sessionTime.toLocalTime();
        session.setFinalTime(Timestamp.valueOf(LocalDateTime.of(today, localSessionTime)));

        session.setApproved(true);

        return sessionRepository.save(session);
    }

    public List<CounsellingSession> getMySessions(int studentId) {
        return sessionRepository.findByStudentId(studentId);
    }

    @Transactional
    public CounsellorRating rateCounsellor(int studentId, int counsellorId, int rating, String feedback) {
        if (studentRepository.findById(studentId).isEmpty()) {
            throw new RuntimeException("Student not found");
        }
        Counsellor counsellor = counsellorRepository.findById(counsellorId);
        if (counsellor == null) {
            throw new RuntimeException("Counsellor not found");
        }

        CounsellorRating newRating = new CounsellorRating();
        newRating.setStudentId(studentId);
        newRating.setCounsellorId(counsellorId);
        newRating.setRating(rating);
        newRating.setFeedback(feedback);
        ratingRepository.save(newRating);

        List<CounsellorRating> allRatings = ratingRepository.findByCounsellorId(counsellorId);
        double average = allRatings.stream().mapToInt(CounsellorRating::getRating).average().orElse(0.0);
        counsellor.setRating(new java.math.BigDecimal(average).setScale(2, java.math.RoundingMode.HALF_UP));
        counsellorRepository.update(counsellor);

        return newRating;
    }
}