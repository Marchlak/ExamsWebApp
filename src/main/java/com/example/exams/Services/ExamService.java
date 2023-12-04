package com.example.exams.Services;


import com.example.exams.Model.Data.db.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.exams.Repositories.Db.ExamRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    public void deleteExam(Integer examId) {
        Optional<Exam> exam = examRepository.findById(examId);
        exam.ifPresent(examRepository::delete);
    }
    @Transactional
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
}
