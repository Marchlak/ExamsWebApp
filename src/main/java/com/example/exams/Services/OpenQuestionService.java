package com.example.exams.Services;

import com.example.exams.Model.Data.db.Openquestion;
import com.example.exams.Repositories.Db.OpenQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OpenQuestionService {
    private final OpenQuestionRepository openQuestionRepository;

    @Autowired
    public OpenQuestionService(OpenQuestionRepository openQuestionRepository){
        this.openQuestionRepository = openQuestionRepository;
    }
    public Openquestion UpdateOpenQuestion(Openquestion updatedOpenQuestion){
        Openquestion openQuestion = GetOpenQuestion(updatedOpenQuestion.getQuestionid());
        if(openQuestion != null){
            openQuestion.setContent(updatedOpenQuestion.getContent());
            openQuestion.setScore(updatedOpenQuestion.getScore());
            openQuestion.setSubject_subjectid(updatedOpenQuestion.getSubject_subjectid());
            return openQuestionRepository.save(openQuestion);
        }
        return null;
    }
    public Openquestion GetOpenQuestion(int questionID){
        Optional<Openquestion> existingOpenQuestionOptional = openQuestionRepository.findById(questionID);
        return existingOpenQuestionOptional.orElse(null);
    }
    public Openquestion AddOpenQuestion(Openquestion newOpenQuestion){
        return openQuestionRepository.save(newOpenQuestion);
    }

    public List<Openquestion> getAll() {
        return openQuestionRepository.findAll();
    }

    public List<Openquestion> getAllByExamId(int examId) {
        return openQuestionRepository.findByExamId(examId);
    }


    @Transactional
    public boolean deleteOpenQuestion(Integer questionID) {
        if (openQuestionRepository.existsById(questionID)) {
            openQuestionRepository.deleteById(questionID);
            return true;
        }
        return false;
    }
}