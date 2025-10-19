package com.thecodealchemist.spring_boot_project.service;

import com.thecodealchemist.spring_boot_project.dao.CounsellorAvailabilityRepository;
import com.thecodealchemist.spring_boot_project.dao.CounsellorRepository;
import com.thecodealchemist.spring_boot_project.dao.CounsellingSessionRepository;
import com.thecodealchemist.spring_boot_project.model.Counsellor;
import com.thecodealchemist.spring_boot_project.model.CounsellorAvailability;
import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import com.thecodealchemist.spring_boot_project.model.CounsellorViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounsellorService {

    @Autowired
    private CounsellorRepository counsellorRepository;

    @Autowired
    private CounsellorAvailabilityRepository availabilityRepository;

    @Autowired
    private CounsellingSessionRepository sessionRepository;

    public Counsellor registerCounsellor(int studentId, String specialization, String selfDescription) {
        Counsellor counsellor = new Counsellor();
        counsellor.setCounsellorId(studentId);
        counsellor.setSpecialization(Counsellor.Specialization.valueOf(specialization));
        counsellor.setSelfDescription(selfDescription);
        return counsellorRepository.save(counsellor);
    }

    public CounsellorAvailability addAvailability(int counsellorId, String availableTime) {
        CounsellorAvailability availability = new CounsellorAvailability();
        availability.setCounsellorId(counsellorId);
        availability.setAvailableTime(Time.valueOf(availableTime));
        availability.setBooked(false);
        return availabilityRepository.save(availability);
    }

    public List<CounsellingSession> getTodayRequests(int counsellorId) {
        return sessionRepository.findByCounsellorIdAndDate(counsellorId, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
    }

    public List<CounsellorViewDTO> getAllCounsellors() {
        return counsellorRepository.findAll().stream()
                .map(c -> new CounsellorViewDTO(c.getCounsellorId(), c.getSpecialization().name(), c.getStudent().getFirstName(), c.getStudent().getLastName(), c.getSelfDescription()))
                .collect(Collectors.toList());
    }

    public CounsellorViewDTO getCounsellorDetails(int counsellorId) {
        Counsellor counsellor = counsellorRepository.findById(counsellorId);
        if (counsellor == null) {
            return null;
        }
        CounsellorViewDTO dto = new CounsellorViewDTO(counsellor.getCounsellorId(), counsellor.getSpecialization().name(), counsellor.getStudent().getFirstName(), counsellor.getStudent().getLastName(), counsellor.getSelfDescription());
        List<String> availableTimes = availabilityRepository.findByCounsellorId(counsellorId)
                .stream()
                .map(a -> a.getAvailableTime().toString())
                .collect(Collectors.toList());
        dto.setAvailableTimes(availableTimes);
        return dto;
    }
}