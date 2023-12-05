package com.example.exams.Services;

import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Repositories.Db.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam AddExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam GetExam(int id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    public void updateExam(Exam updatedExam) {
        examRepository.save(updatedExam);
    }

    @Transactional
    public boolean deleteExam(Integer examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return true;
        }
        return false;
    }

}
