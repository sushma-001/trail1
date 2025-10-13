package com.thecodealchemist.spring_boot_project.dao;

import com.thecodealchemist.spring_boot_project.model.Counsellor;
import com.thecodealchemist.spring_boot_project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CounsellorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentRepository studentRepository;

    private RowMapper<Counsellor> rowMapper = (rs, rowNum) -> {
        Counsellor counsellor = new Counsellor();
        counsellor.setCounsellorId(rs.getInt("counsellor_id"));
        counsellor.setSpecialization(Counsellor.Specialization.valueOf(rs.getString("specialization")));
        counsellor.setNoOfStudentsCounselled(rs.getInt("no_of_students_counselled"));
        counsellor.setSelfDescription(rs.getString("self_description"));
        counsellor.setRating(rs.getBigDecimal("rating"));
        counsellor.setJoinedAt(rs.getTimestamp("joined_at"));

        Student student = studentRepository.findById(rs.getInt("counsellor_id")).orElse(null);
        counsellor.setStudent(student);

        return counsellor;
    };

    public List<Counsellor> findAll() {
        String sql = "SELECT * FROM Counsellor";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Counsellor findById(int id) {
        String sql = "SELECT * FROM Counsellor WHERE counsellor_id = ?";
        List<Counsellor> counsellors = jdbcTemplate.query(sql, rowMapper, id);
        return counsellors.isEmpty() ? null : counsellors.get(0);
    }

    public Counsellor save(Counsellor counsellor) {
        String sql = "INSERT INTO Counsellor (counsellor_id, specialization, self_description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                counsellor.getCounsellorId(),
                counsellor.getSpecialization().name(),
                counsellor.getSelfDescription()
        );
        return counsellor;
    }

    public void update(Counsellor counsellor) {
        String sql = "UPDATE Counsellor SET rating = ? WHERE counsellor_id = ?";
        jdbcTemplate.update(sql, counsellor.getRating(), counsellor.getCounsellorId());
    }
}