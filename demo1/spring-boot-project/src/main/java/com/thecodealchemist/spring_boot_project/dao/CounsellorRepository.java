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

    private final RowMapper<Counsellor> rowMapper = (rs, rowNum) -> {
        Counsellor counsellor = new Counsellor();
        counsellor.setCounsellorId(rs.getInt("counsellor_id"));
        counsellor.setSpecialization(Counsellor.Specialization.valueOf(rs.getString("specialization")));
        counsellor.setNoOfStudentsCounselled(rs.getInt("no_of_students_counselled"));
        counsellor.setSelfDescription(rs.getString("self_description"));
        counsellor.setJoinedAt(rs.getTimestamp("joined_at"));
        return counsellor;
    };

    public Counsellor save(Counsellor counsellor) {
        String sql = "INSERT INTO Counsellor (counsellor_id, specialization, self_description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, counsellor.getCounsellorId(), counsellor.getSpecialization().name(), counsellor.getSelfDescription());
        return counsellor;
    }

    public List<Counsellor> findAll() {
        String sql = "SELECT c.*, s.first_name, s.last_name FROM Counsellor c JOIN Student s ON c.counsellor_id = s.student_id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Counsellor counsellor = rowMapper.mapRow(rs, rowNum);
            Student student = new Student();
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            counsellor.setStudent(student);
            return counsellor;
        });
    }

    public Counsellor findById(int id) {
        String sql = "SELECT c.*, s.first_name, s.last_name FROM Counsellor c JOIN Student s ON c.counsellor_id = s.student_id WHERE c.counsellor_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Counsellor counsellor = rowMapper.mapRow(rs, rowNum);
                Student student = new Student();
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                counsellor.setStudent(student);
                return counsellor;
            });
        } catch (Exception e) {
            return null;
        }
    }

    public void update(Counsellor counsellor) {
        String sql = "UPDATE Counsellor SET specialization = ?, self_description = ? WHERE counsellor_id = ?";
        jdbcTemplate.update(sql, counsellor.getSpecialization().name(), counsellor.getSelfDescription(), counsellor.getCounsellorId());
    }
}