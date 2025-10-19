package com.thecodealchemist.spring_boot_project.dao;

import com.thecodealchemist.spring_boot_project.model.CounsellingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CounsellingSessionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CounsellingSession save(CounsellingSession session) {
        String sql = "INSERT INTO CounsellingSession (counsellor_id, student_id, service_id, is_approved, counselling_mode, final_time) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.getCounsellorId(), session.getStudentId(), session.getServiceId(), session.isApproved(), session.getCounsellingMode().name(), session.getFinalTime());
        return session;
    }

    public List<CounsellingSession> findByCounsellorIdAndDate(int counsellorId, Timestamp date) {
        String sql = "SELECT * FROM CounsellingSession WHERE counsellor_id = ? AND DATE(final_time) = ?";
        return jdbcTemplate.query(sql, new Object[]{counsellorId, date}, (rs, rowNum) -> {
            CounsellingSession session = new CounsellingSession();
            session.setSessionId(rs.getInt("session_id"));
            session.setCounsellorId(rs.getInt("counsellor_id"));
            session.setStudentId(rs.getInt("student_id"));
            session.setServiceId(rs.getInt("service_id"));
            session.setApproved(rs.getBoolean("is_approved"));
            session.setCounsellingMode(CounsellingSession.CounsellingMode.valueOf(rs.getString("counselling_mode")));
            session.setFinalTime(rs.getTimestamp("final_time"));
            return session;
        });
    }

    public List<CounsellingSession> findByStudentId(int studentId) {
        String sql = "SELECT * FROM CounsellingSession WHERE student_id = ?";
        return jdbcTemplate.query(sql, new Object[]{studentId}, (rs, rowNum) -> {
            CounsellingSession session = new CounsellingSession();
            session.setSessionId(rs.getInt("session_id"));
            session.setCounsellorId(rs.getInt("counsellor_id"));
            session.setStudentId(rs.getInt("student_id"));
            session.setServiceId(rs.getInt("service_id"));
            session.setApproved(rs.getBoolean("is_approved"));
            session.setCounsellingMode(CounsellingSession.CounsellingMode.valueOf(rs.getString("counselling_mode")));
            session.setFinalTime(rs.getTimestamp("final_time"));
            return session;
        });
    }
}