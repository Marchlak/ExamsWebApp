package com.example.exams.Repositories.Db;


import com.example.exams.Model.Data.db.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
