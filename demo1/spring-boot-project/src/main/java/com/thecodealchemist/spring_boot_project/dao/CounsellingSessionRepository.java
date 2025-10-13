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
        session.setStudentId(rs.getInt("student_id"));
        session.setCounsellorId(rs.getInt("counsellor_id"));
        session.setFinalTime(rs.getTimestamp("final_time"));
        session.setApproved(rs.getBoolean("is_approved"));
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
        String sql = "INSERT INTO CounsellingSession (student_id, counsellor_id, final_time, is_approved) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.getStudentId(), session.getCounsellorId(), session.getFinalTime(), session.isApproved());
        return session;
    }
}