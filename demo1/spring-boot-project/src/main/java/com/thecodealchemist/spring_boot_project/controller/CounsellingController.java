package com.thecodealchemist.spring_boot_project.controller;

import com.thecodealchemist.spring_boot_project.service.CounsellingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/counselling")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class CounsellingController {

    @Autowired
    private CounsellingService counsellingService;

    @PostMapping("/book")
    public ResponseEntity<?> bookSession(@RequestBody Map<String, String> payload, HttpSession session) {
        Integer studentId = (Integer) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "You must be logged in to book a session"));
        }
        int counsellorId = Integer.parseInt(payload.get("counsellorId"));
        String sessionTime = payload.get("sessionTime");
        var result = counsellingService.bookSession(studentId, counsellorId, sessionTime);
        return ResponseEntity.ok(Map.of("status", "success", "message", "✅ Appointment approved", "data", result));
    }

    @GetMapping("/my-sessions")
    public ResponseEntity<?> getMySessions(HttpSession session) {
        Integer studentId = (Integer) session.getAttribute("studentId");
        if (studentId == null) {
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "You must be logged in to view your sessions"));
        }
        var result = counsellingService.getMySessions(studentId);
        return ResponseEntity.ok(Map.of("status", "success", "data", result));
    }
}