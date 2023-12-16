package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Model.Data.db.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogstudentexamRepository extends JpaRepository<Logstudentexam, Integer> {
    @Query("SELECT l FROM Logstudentexam l WHERE l.studentStudent.studentId = :studentId")
    List<Logstudentexam> findByStudentStudentId(Integer studentId);

    Logstudentexam findLogstudentexamByStudentStudentAndExamExamid(Student studentStudent, Exam examExamid);

    List<Logstudentexam> findLogstudentexamsByExamExamid(Exam examExamid);

}
