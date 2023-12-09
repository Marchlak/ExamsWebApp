package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {
    Examiner findExaminerByLogin(String login);
}
