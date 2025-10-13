package com.thecodealchemist.spring_boot_project.controller;

import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import com.thecodealchemist.spring_boot_project.model.CounsellorRating;
import com.thecodealchemist.spring_boot_project.service.CounsellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/counselling")
public class CounsellingController {

    @Autowired
    private CounsellingService counsellingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookSession(@RequestBody Map<String, Object> payload) {
        try {
            int studentId = (Integer) payload.get("studentId");
            int counsellorId = (Integer) payload.get("counsellorId");
            Time sessionTime = Time.valueOf((String) payload.get("sessionTime"));

            CounsellingSession session = counsellingService.bookSession(studentId, counsellorId, sessionTime);
            return ResponseEntity.ok(Map.of("status", "success", "message", "✅ Appointment approved", "data", session));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @GetMapping("/my-sessions/{student_id}")
    public ResponseEntity<?> getMySessions(@PathVariable("student_id") int studentId) {
        List<CounsellingSession> sessions = counsellingService.getMySessions(studentId);
        return ResponseEntity.ok(Map.of("status", "success", "data", sessions));
    }

    @PostMapping("/rate")
    public ResponseEntity<?> rateCounsellor(@RequestBody Map<String, Object> payload) {
        try {
            int studentId = (Integer) payload.get("studentId");
            int counsellorId = (Integer) payload.get("counsellorId");
            int rating = (Integer) payload.get("rating");
            String feedback = (String) payload.get("feedback");

            CounsellorRating newRating = counsellingService.rateCounsellor(studentId, counsellorId, rating, feedback);
            return ResponseEntity.ok(Map.of("status", "success", "message", "Rating submitted successfully", "data", newRating));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
        }
    }
}