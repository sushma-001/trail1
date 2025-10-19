package com.thecodealchemist.spring_boot_project.controller;

import com.thecodealchemist.spring_boot_project.model.CounsellorViewDTO;
import com.thecodealchemist.spring_boot_project.service.CounsellorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/counsellor")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCounsellor(@RequestBody Map<String, String> payload, HttpSession session) {
        Integer studentId = (Integer) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "You must be logged in"));
        }
        String specialization = payload.get("specialization");
        String selfDescription = payload.get("selfDescription");
        var result = counsellorService.registerCounsellor(studentId, specialization, selfDescription);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Counsellor registration successful", "data", result));
    }

    @PostMapping("/availability")
    public ResponseEntity<?> addAvailability(@RequestBody Map<String, String> payload, HttpSession session) {
        Integer counsellorId = (Integer) session.getAttribute("studentId");
        if (counsellorId == null) {
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "You must be logged in as a counsellor"));
        }
        String availableTime = payload.get("availableTime");
        var result = counsellorService.addAvailability(counsellorId, availableTime);
        return ResponseEntity.ok(Map.of("status", "success", "message", "Availability added successfully", "data", result));
    }

    @GetMapping("/requests")
    public ResponseEntity<?> getTodayRequests(HttpSession session) {
        Integer counsellorId = (Integer) session.getAttribute("studentId");
        if (counsellorId == null) {
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "You must be logged in as a counsellor"));
        }
        var result = counsellorService.getTodayRequests(counsellorId);
        return ResponseEntity.ok(Map.of("status", "success", "data", result));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCounsellors() {
        List<CounsellorViewDTO> result = counsellorService.getAllCounsellors();
        return ResponseEntity.ok(Map.of("status", "success", "data", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCounsellorDetails(@PathVariable int id) {
        CounsellorViewDTO result = counsellorService.getCounsellorDetails(id);
        return ResponseEntity.ok(Map.of("status", "success", "data", result));
    }
}