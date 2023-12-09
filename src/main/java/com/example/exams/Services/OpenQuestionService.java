package com.example.exams.Services;

import com.example.exams.Model.Data.db.OpenQuestion;
import com.example.exams.Repositories.Db.OpenQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OpenQuestionService {
    private final OpenQuestionRepository openQuestionRepository;

    @Autowired
    public OpenQuestionService(OpenQuestionRepository openQuestionRepository){
        this.openQuestionRepository = openQuestionRepository;
    }

    public OpenQuestion UpdateOpenQuestion(OpenQuestion updatedOpenQuestion){
        OpenQuestion openQuestion = GetOpenQuestion(updatedOpenQuestion.getOpenQuestionId());
        if(openQuestion != null){
            openQuestion.setContent(updatedOpenQuestion.getContent());
            openQuestion.setScore(updatedOpenQuestion.getScore());
            openQuestion.setQuestionSubject(updatedOpenQuestion.getQuestionSubject());
            return openQuestionRepository.save(openQuestion);
        }
        return null;
    }

    public OpenQuestion GetOpenQuestion(int questionID){
        Optional<OpenQuestion> existingOpenQuestionOptional = openQuestionRepository.findById(questionID);
        return existingOpenQuestionOptional.orElse(null);
    }

    public OpenQuestion AddOpenQuestion(OpenQuestion newOpenQuestion){
        return openQuestionRepository.save(newOpenQuestion);
    }

    public List<OpenQuestion> getAll() {
        return openQuestionRepository.findAll();
    }

    public List<OpenQuestion> getAllByExamId(int examId) {
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