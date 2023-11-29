package com.example.exams.Repositories;

import com.example.exams.Model.Data.db.Closedquestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClosedquestionRepository extends JpaRepository<Closedquestion, Integer> {
}