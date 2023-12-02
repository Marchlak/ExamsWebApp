package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Openquestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenQuestionRepository extends JpaRepository<Openquestion, Integer> {
}