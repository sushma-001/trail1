package com.thecodealchemist.spring_boot_project.dao;

import com.thecodealchemist.spring_boot_project.model.CounsellorAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public class CounsellorAvailabilityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CounsellorAvailability save(CounsellorAvailability availability) {
        String sql = "INSERT INTO CounsellorAvailability (counsellor_id, available_time, is_booked) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, availability.getCounsellorId(), availability.getAvailableTime(), availability.isBooked());
        return availability;
    }

    public List<CounsellorAvailability> findByCounsellorId(int counsellorId) {
        String sql = "SELECT * FROM CounsellorAvailability WHERE counsellor_id = ? AND is_booked = false";
        return jdbcTemplate.query(sql, new Object[]{counsellorId}, (rs, rowNum) -> {
            CounsellorAvailability availability = new CounsellorAvailability();
            availability.setCounsellorId(rs.getInt("counsellor_id"));
            availability.setAvailableTime(rs.getTime("available_time"));
            availability.setBooked(rs.getBoolean("is_booked"));
            return availability;
        });
    }

    public void update(CounsellorAvailability availability) {
        String sql = "UPDATE CounsellorAvailability SET is_booked = ? WHERE counsellor_id = ? AND available_time = ?";
        jdbcTemplate.update(sql, availability.isBooked(), availability.getCounsellorId(), availability.getAvailableTime());
    }

    public CounsellorAvailability findById(int counsellorId, Time availableTime) {
        String sql = "SELECT * FROM CounsellorAvailability WHERE counsellor_id = ? AND available_time = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{counsellorId, availableTime}, (rs, rowNum) -> {
                CounsellorAvailability availability = new CounsellorAvailability();
                availability.setCounsellorId(rs.getInt("counsellor_id"));
                availability.setAvailableTime(rs.getTime("available_time"));
                availability.setBooked(rs.getBoolean("is_booked"));
                return availability;
            });
        } catch (Exception e) {
            return null;
        }
    }
}