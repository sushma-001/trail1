package com.thecodealchemist.spring_boot_project.dao;

import com.thecodealchemist.spring_boot_project.model.CounsellorRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CounsellorRatingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<CounsellorRating> rowMapper = (rs, rowNum) -> {
        CounsellorRating rating = new CounsellorRating();
        rating.setCounsellorId(rs.getInt("counsellor_id"));
        rating.setStudentId(rs.getInt("student_id"));
        rating.setRating(rs.getInt("rating"));
        rating.setFeedback(rs.getString("feedback"));
        rating.setRatedAt(rs.getTimestamp("rated_at"));
        return rating;
    };

    public CounsellorRating save(CounsellorRating rating) {
        String sql = "INSERT INTO CounsellorRating (counsellor_id, student_id, rating, feedback) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                rating.getCounsellorId(),
                rating.getStudentId(),
                rating.getRating(),
                rating.getFeedback()
        );
        return rating;
    }

    public List<CounsellorRating> findByCounsellorId(int counsellorId) {
        String sql = "SELECT * FROM CounsellorRating WHERE counsellor_id = ?";
        return jdbcTemplate.query(sql, rowMapper, counsellorId);
    }
}