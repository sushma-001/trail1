package com.thecodealchemist.spring_boot_project.dao;

import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CounsellingSessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<CounsellingSession> rowMapper = (rs, rowNum) -> {
        CounsellingSession session = new CounsellingSession();
        session.setCounsellorId(rs.getInt("counsellor_id"));
        session.setServiceId(rs.getInt("service_id"));
        session.setStudentId(rs.getInt("student_id"));
        session.setApproved(rs.getBoolean("is_approved"));
        session.setTimeOptions(rs.getString("time_options"));
        session.setCounsellingMode(CounsellingSession.CounsellingMode.valueOf(rs.getString("counselling_mode")));
        session.setFinalTime(rs.getTimestamp("final_time"));
        return session;
    };

    public List<CounsellingSession> findByStudentId(int studentId) {
        String sql = "SELECT * FROM CounsellingSession WHERE student_id = ?";
        return jdbcTemplate.query(sql, rowMapper, studentId);
    }

    public List<CounsellingSession> findByCounsellorIdAndDate(int counsellorId, Timestamp date) {
        String sql = "SELECT * FROM CounsellingSession WHERE counsellor_id = ? AND DATE(final_time) = ?";
        return jdbcTemplate.query(sql, rowMapper, counsellorId, new java.sql.Date(date.getTime()));
    }

    public CounsellingSession save(CounsellingSession session) {
        String sql = "INSERT INTO CounsellingSession (counsellor_id, service_id, student_id, is_approved, time_options, counselling_mode, final_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.getCounsellorId(), session.getServiceId(), session.getStudentId(), session.isApproved(), session.getTimeOptions(), session.getCounsellingMode().name(), session.getFinalTime());
        return session;
    }
}