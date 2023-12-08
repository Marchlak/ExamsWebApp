package com.example.exams.Services;

import com.example.exams.Model.Data.db.Answerclosed;
import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Model.Data.db.Openquestion;
import com.example.exams.Repositories.Db.AnswerClosedRepository;
import com.example.exams.Repositories.Db.ClosedQuestionRepository;
import com.example.exams.Repositories.Db.OpenQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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