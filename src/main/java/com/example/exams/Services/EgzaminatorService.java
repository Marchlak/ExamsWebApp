package com.example.exams.Services;

import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Repositories.Db.EgzaminatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EgzaminatorService {
    private final EgzaminatorRepository egzaminatorRepository;

    @Autowired
    public EgzaminatorService(EgzaminatorRepository egzaminatorRepository){
        this.egzaminatorRepository = egzaminatorRepository;
    }

    public Egzaminator Get(int id){
        Optional <Egzaminator> egzaminator = egzaminatorRepository.findById(id);
        return egzaminator.orElse(null);
    }
}
