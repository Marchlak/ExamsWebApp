package com.example.exams.Services;

import com.example.exams.Model.Data.db.Openquestion;
import com.example.exams.Model.Data.db.Subject;
import com.example.exams.Repositories.Db.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> GetAll(){
        return subjectRepository.findAll();
    }
    public Subject Get(int questionID){
        Optional<Subject> existingSubjectOptional = subjectRepository.findById(questionID);
        return existingSubjectOptional.orElse(null);
    }
}