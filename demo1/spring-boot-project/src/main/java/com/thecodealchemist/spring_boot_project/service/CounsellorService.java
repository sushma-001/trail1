package com.thecodealchemist.spring_boot_project.service;

import com.thecodealchemist.spring_boot_project.dao.CounsellorAvailabilityRepository;
import com.thecodealchemist.spring_boot_project.dao.CounsellorRepository;
import com.thecodealchemist.spring_boot_project.dao.CounsellingSessionRepository;
import com.thecodealchemist.spring_boot_project.dao.StudentRepository;
import com.thecodealchemist.spring_boot_project.model.Counsellor;
import com.thecodealchemist.spring_boot_project.model.CounsellorAvailability;
import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import com.thecodealchemist.spring_boot_project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class CounsellorService {

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CounsellorAvailabilityRepository availabilityRepository;

    @Autowired
    private CounsellingSessionRepository sessionRepository;

    public Counsellor registerCounsellor(Counsellor counsellor) {
        Student student = studentRepository.findById(counsellor.getCounsellorId()).orElse(null);
        if (student == null) {
            throw new RuntimeException("Student not found");
        }
        counsellor.setStudent(student);
        return counsellorRepository.save(counsellor);
    }

    public CounsellorAvailability addAvailability(int counsellorId, Time availableTime) {
        Counsellor counsellor = counsellorRepository.findById(counsellorId);
        if (counsellor == null) {
            throw new RuntimeException("Counsellor not found");
        }

        CounsellorAvailability availability = new CounsellorAvailability();
        availability.setCounsellorId(counsellorId);
        availability.setAvailableTime(availableTime);
        availability.setBooked(false);

        return availabilityRepository.save(availability);
    }

    public List<CounsellingSession> getTodayRequests(int counsellorId) {
        return sessionRepository.findByCounsellorIdAndDate(counsellorId, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
    }

    public List<Counsellor> getAllCounsellors() {
        return counsellorRepository.findAll();
    }

    public Counsellor getCounsellorDetails(int counsellorId) {
        Counsellor counsellor = counsellorRepository.findById(counsellorId);
        if (counsellor != null) {
            counsellor.setAvailableTimes(availabilityRepository.findByCounsellorId(counsellorId));
        }
        return counsellor;
    }
}