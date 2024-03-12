package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Model.Data.db.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {
    Examiner findExaminerByLogin(String login);
    Examiner findByLogin(String login);


    List<Examiner> findAll();
}
