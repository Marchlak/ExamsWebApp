package com.example.exams.Services;

import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Repositories.Db.ExaminerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminerService {
    private final ExaminerRepository examinersRepository;

    @Autowired
    public ExaminerService(ExaminerRepository examinersRepository){
        this.examinersRepository = examinersRepository;
    }

    public List<Examiner> getAllExaminers() {
        return examinersRepository.findAll();
    }

    public Examiner Get(int id){
        Optional <Examiner> examiner = examinersRepository.findById(id);
        return examiner.orElse(null);
    }

    public Examiner findByLogin(String name){
        return examinersRepository.findByLogin(name);
    }

    public void activate(int id){
        Optional <Examiner> examiner = examinersRepository.findById(id);
        if(examiner.isPresent()){
            examiner.get().setVerificationStatus(true);
            examinersRepository.save(examiner.get());
        }
    }

    public void deactivate(int id){
        Optional <Examiner> examiner = examinersRepository.findById(id);
        if(examiner.isPresent()){
            examiner.get().setVerificationStatus(false);
            examinersRepository.save(examiner.get());
        }
    }
}
