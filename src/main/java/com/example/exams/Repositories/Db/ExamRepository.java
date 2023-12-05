package com.example.exams.Repositories.Db;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exams.Model.Data.db.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}