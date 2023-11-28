package com.example.exams.Repositories;

import com.example.exams.Model.Data.Openquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpenquestionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Openquestion> getAll() {
        List<Openquestion> query = jdbcTemplate.query("SELECT questionid,content,score,subject_subjectid FROM openquestion",
                BeanPropertyRowMapper.newInstance(Openquestion.class));
        return query;
    }

    public void add(Openquestion openquestion) {
        jdbcTemplate.update("INSERT INTO openquestion(content,score) VALUES(?,?)",
                        openquestion.getContent(),openquestion.getScore());
    }
}
