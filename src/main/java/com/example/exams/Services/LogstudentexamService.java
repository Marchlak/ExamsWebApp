package com.example.exams.Services;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Logstudentexam;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.LogstudentexamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogstudentexamService {

    private final LogstudentexamRepository logstudentexamRepository;

    @Autowired
    public LogstudentexamService(LogstudentexamRepository logstudentexamRepository) {
        this.logstudentexamRepository = logstudentexamRepository;
    }

    public List<Logstudentexam> getStudentExamHistory(Integer id) {
        return logstudentexamRepository.findByStudentStudentId(id);
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
        newLogstudentexam.setExamExamid(examExamid);
        newLogstudentexam.setStudentStudent(studentStudent);
        logstudentexamRepository.save(newLogstudentexam);
        return newLogstudentexam.getId();
    }
}


