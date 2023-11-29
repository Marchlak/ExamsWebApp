package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.Openquestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenQuestionRepository extends JpaRepository<Openquestion, Integer> {
}
