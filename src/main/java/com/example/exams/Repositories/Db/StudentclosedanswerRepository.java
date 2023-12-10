package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.Studentclosedanswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentclosedanswerRepository extends JpaRepository<Studentclosedanswer, Integer> {
    List<Studentclosedanswer> findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id(int examId);

}