package com.example.exams.Services;

//import com.example.exams.Model.Data.ProperDataModels.ProblemDTO;

import com.example.exams.Model.Data.db.Problem;
import com.example.exams.Repositories.Db.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
    ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem AddOne(Problem problem) {
        return problemRepository.save(problem);
    }

    public List<Problem> GetAll() {
        return problemRepository.findAll();
    }

    public Problem findById(int id) {
        return problemRepository.getReferenceById(id);
    }

    public Problem changeStatus(int id, String newStatus) {
        Problem problem = findById(id);
        problem.setStatus(newStatus);
        return problemRepository.save(problem);
    }
}
