package com.example.exams.Services;

import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Repositories.Db.ExaminerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExaminerService {
    private final ExaminerRepository examinersRepository;

    @Autowired
    public ExaminerService(ExaminerRepository examiexaminersRepository){
        this.examinersRepository = examiexaminersRepository;
    }

    public Examiner Get(int id){
        Optional <Examiner> examiner = examinersRepository.findById(id);
        return examiner.orElse(null);
    }

    public Examiner findByLogin(String name){
        return examinersRepository.findByLogin(name);
    }
}
