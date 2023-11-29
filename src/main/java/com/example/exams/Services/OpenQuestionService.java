package com.example.exams.Services;

import com.example.exams.Model.Data.MN.Openquestion;
import com.example.exams.Repositories.Db.OpenQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            openQuestion.setSubjectsubject_subjectid(updatedOpenQuestion.getSubjectsubject_subjectid());
            return openQuestionRepository.save(openQuestion);
        }
        return null;
    }
    public Openquestion GetOpenQuestion(int questionID){
        Optional<Openquestion> existingOpenQuestionOptional = openQuestionRepository.findById(questionID);
        return existingOpenQuestionOptional.orElse(null);
    }
}