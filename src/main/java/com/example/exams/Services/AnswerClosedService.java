package com.example.exams.Services;

import com.example.exams.Model.Data.db.Answerclosed;
import com.example.exams.Repositories.Db.AnswerClosedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerClosedService {
    private final AnswerClosedRepository answerClosedRepository;

    @Autowired
    public AnswerClosedService(AnswerClosedRepository answerClosedRepository){
        this.answerClosedRepository = answerClosedRepository;
    }
    public List<Answerclosed> getAllByQuestionId(int questionId) {
        return answerClosedRepository.findByClosedquestionQuestionid_Id(questionId);
    }
}