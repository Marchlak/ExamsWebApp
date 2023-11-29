package com.example.exams.Services;

import com.example.exams.Repositories.OpenquestionRepository;
import com.example.exams.Repositories.ClosedquestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {

    @Autowired
    private OpenquestionRepository openQuestionRepository;

    @Autowired
    private ClosedquestionRepository closedQuestionRepository;

    @Transactional
    public void deleteOpenQuestion(Integer id) {

    }

    @Transactional
    public void deleteClosedQuestion(Integer id) {
        closedQuestionRepository.deleteById(id);
    }
}