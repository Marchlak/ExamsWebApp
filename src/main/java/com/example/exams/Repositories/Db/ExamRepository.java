package com.example.exams.Repositories.Db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exams.Model.Data.db.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
}