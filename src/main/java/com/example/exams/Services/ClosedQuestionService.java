package com.example.exams.Services;

import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Repositories.Db.ClosedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClosedQuestionService {
    private final ClosedQuestionRepository closedQuestionRepository;

    @Autowired
    public ClosedQuestionService(ClosedQuestionRepository closedQuestionRepository){
        this.closedQuestionRepository = closedQuestionRepository;
    }
    public List<Closedquestion> getAllByExamId(int examId) {
        return closedQuestionRepository.findByExamId(examId);
    }

    public Closedquestion addClosedQuestion(Closedquestion newClosedQuestion)
    {
        return closedQuestionRepository.save(newClosedQuestion);
    }

    public Closedquestion getClosedQuestionById(Integer questionId) {
        return closedQuestionRepository.findById(questionId).orElse(null);
    }
}