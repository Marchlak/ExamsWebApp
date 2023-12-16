package com.example.exams.Services;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.LogstudentexamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class LogstudentexamService {
    private final LogstudentexamRepository logstudentexamRepository;

    public LogstudentexamService(LogstudentexamRepository logstudentexamRepository) {
        this.logstudentexamRepository = logstudentexamRepository;
    }

    public List<Logstudentexam> getStudentExamHistory(Integer id) {
        return logstudentexamRepository.findByStudentStudentId(id);
    }

    public List<Logstudentexam> getStudentsLogstudentExamById(Exam exam) {
        return logstudentexamRepository.findLogstudentexamsByExamExamid(exam);
    }

    public void setDateTimeExaminerComment(Student student, Exam exam, String examinerComment){
        Logstudentexam logstudentexam = logstudentexamRepository.findLogstudentexamByStudentStudentAndExamExamid(student, exam);
        logstudentexam.setDate(LocalDate.now());
        logstudentexam.setTime(LocalTime.now());
        logstudentexam.setDescription(examinerComment);
        logstudentexamRepository.save(logstudentexam);
    }


    public void addOpenPoints(Student student, Exam exam, Integer openPoints){
        Logstudentexam logstudentexam = logstudentexamRepository.findLogstudentexamByStudentStudentAndExamExamid(student, exam);
        int score;

        if (logstudentexam.getScoreresult() == null){
            score = 0;
        }else{
            score = logstudentexam.getScoreresult();
        }
        int finalResult = score + openPoints;
        logstudentexam.setScoreresult(finalResult);
        logstudentexamRepository.save(logstudentexam);
    }


    public void addPointsToLogstudentexam(int logstudentexamId, int points) {
        Logstudentexam logstudentexam = logstudentexamRepository.findById(logstudentexamId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono Logstudentexam o ID: " + logstudentexamId));

        logstudentexam.addPoints(points);
        logstudentexamRepository.save(logstudentexam);
    }

    public Integer createAndSaveLogstudentexam(Exam examExamid, Student studentStudent) {
        Logstudentexam newLogstudentexam = new Logstudentexam();
        newLogstudentexam.setDate(LocalDate.now());
        newLogstudentexam.setTime(LocalTime.now());
        newLogstudentexam.setExamExamid(examExamid);
        newLogstudentexam.setStudentStudent(studentStudent);
        logstudentexamRepository.save(newLogstudentexam);
        return newLogstudentexam.getId();
    }
    @Transactional
    public void deleteAllLogsForExam(Exam exam) {
        logstudentexamRepository.deleteByExamExamid(exam);
    }

    public boolean existsByExamIdAndStudentId(Integer examId, Integer studentId) {
        return logstudentexamRepository.existsByExamExamid_IdAndStudentStudent_Id(examId, studentId);
    }
}


