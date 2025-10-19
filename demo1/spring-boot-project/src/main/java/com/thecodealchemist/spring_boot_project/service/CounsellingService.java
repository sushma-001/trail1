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
import java.util.stream.Collectors;

@Service
public class CounsellingService {

    @Autowired
    private CounsellingSessionRepository sessionRepository;

    @Autowired
    private CounsellorAvailabilityRepository availabilityRepository;

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public CounsellingSession bookSession(int studentId, int counsellorId, String sessionTime) {
        CounsellorAvailability availability = availabilityRepository.findById(counsellorId, Time.valueOf(sessionTime));
        if (availability == null || availability.isBooked()) {
            throw new RuntimeException("Time slot not available or already booked");
        }

        availability.setBooked(true);
        availabilityRepository.update(availability);

        CounsellingSession session = new CounsellingSession();
        session.setStudentId(studentId);
        session.setCounsellorId(counsellorId);
        session.setServiceId(3); // Assuming 'Mentorship' service_id is 3
        session.setApproved(true);
        session.setCounsellingMode(CounsellingSession.CounsellingMode.Online);

        LocalDate today = LocalDate.now();
        LocalTime localSessionTime = Time.valueOf(sessionTime).toLocalTime();
        session.setFinalTime(Timestamp.valueOf(LocalDateTime.of(today, localSessionTime)));

        return sessionRepository.save(session);
    }

    public List<SessionViewDTO> getMySessions(int studentId) {
        return sessionRepository.findByStudentId(studentId).stream()
                .map(session -> {
                    Counsellor counsellor = counsellorRepository.findById(session.getCounsellorId());
                    return new SessionViewDTO(
                            counsellor.getStudent().getFirstName() + " " + counsellor.getStudent().getLastName(),
                            counsellor.getSpecialization().name(),
                            session.getFinalTime()
                    );
                }).collect(Collectors.toList());
    }
}