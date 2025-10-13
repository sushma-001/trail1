package com.thecodealchemist.spring_boot_project.controller;

import com.thecodealchemist.spring_boot_project.model.Counsellor;
import com.thecodealchemist.spring_boot_project.model.CounsellorAvailability;
import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import com.thecodealchemist.spring_boot_project.service.CounsellorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/counsellor")
public class CounsellorController {

    @Autowired
    private CounsellorService counsellorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCounsellor(@RequestBody Counsellor counsellor) {
        try {
            Counsellor newCounsellor = counsellorService.registerCounsellor(counsellor);
            return ResponseEntity.ok(Map.of("status", "success", "message", "Counsellor registered successfully", "data", newCounsellor));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @PostMapping("/{id}/availability")
    public ResponseEntity<?> addAvailability(@PathVariable("id") int counsellorId, @RequestBody Map<String, String> payload) {
        try {
            Time availableTime = Time.valueOf(payload.get("availableTime"));
            CounsellorAvailability availability = counsellorService.addAvailability(counsellorId, availableTime);
            return ResponseEntity.ok(Map.of("status", "success", "message", "Availability added successfully", "data", availability));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<?> getTodayRequests(@PathVariable("id") int counsellorId) {
        List<CounsellingSession> requests = counsellorService.getTodayRequests(counsellorId);
        return ResponseEntity.ok(Map.of("status", "success", "data", requests));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCounsellors() {
        List<Counsellor> counsellors = counsellorService.getAllCounsellors();
        return ResponseEntity.ok(Map.of("status", "success", "data", counsellors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCounsellorDetails(@PathVariable("id") int counsellorId) {
        Counsellor counsellor = counsellorService.getCounsellorDetails(counsellorId);
        if (counsellor == null) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "Counsellor not found"));
        }
        return ResponseEntity.ok(Map.of("status", "success", "data", counsellor));
    }
}