package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Answerclosed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerClosedRepository extends JpaRepository<Answerclosed, Integer> {

    List<Answerclosed> findByClosedquestionQuestionid_Id(Integer questionId);
}