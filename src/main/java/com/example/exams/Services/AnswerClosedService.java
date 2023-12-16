package com.example.exams.Services;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.AnswerClosedRepository;
import com.example.exams.Repositories.Db.ClosedQuestionRepository;
import com.example.exams.Repositories.Db.StudentclosedanswerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnswerClosedService {
    private final AnswerClosedRepository answerClosedRepository;
    private final StudentclosedanswerRepository studentclosedanswerRepository;
    private final ClosedQuestionRepository closedQuestionRepository;

    @Autowired
    public AnswerClosedService(ClosedQuestionRepository closedQuestionRepository, AnswerClosedRepository answerClosedRepository, StudentclosedanswerRepository studentclosedanswerRepository){
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerClosedRepository = answerClosedRepository;
        this.studentclosedanswerRepository = studentclosedanswerRepository;
    }
    public List<Answerclosed> getAllByQuestionId(int questionId) {
        return answerClosedRepository.findByClosedquestionQuestionid_Id(questionId);
    }

    public List<Student> getAllDistinctStudentsForExam(int examId) {
        List<Studentclosedanswer> studentclosedanswers = studentclosedanswerRepository.findAllByAnswerclosedAnswerid_ClosedquestionQuestionid_Exam_Id(examId);

        Set<Student> uniqueStudents = studentclosedanswers.stream()
                .map(Studentclosedanswer::getStudentStudent)
                .collect(Collectors.toSet());

        return List.copyOf(uniqueStudents);
    }
    public Answerclosed addAnswerClosed(Answerclosed newAnswerClosed){
        return answerClosedRepository.save(newAnswerClosed);
    }

}