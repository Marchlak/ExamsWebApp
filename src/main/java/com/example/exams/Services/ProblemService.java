package com.example.exams.Services;

//import com.example.exams.Model.Data.ProperDataModels.ProblemDTO;

import com.example.exams.Model.Data.ProperDataModels.ShowProblem;
import com.example.exams.Model.Data.db.Problem;
import com.example.exams.Repositories.Db.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public Map<String, List<ShowProblem>> getProblemsBySelectedCategory(Map<String, List<ShowProblem>> problemsByCategory, String selectedCategory) {
        Map<String, List<ShowProblem>> filteredProblemsByCategory = new HashMap<>();
        if (selectedCategory == null || selectedCategory.equals("")) {
            return new HashMap<>(problemsByCategory);
        } else {
            filteredProblemsByCategory.put(selectedCategory, problemsByCategory.getOrDefault(selectedCategory, Collections.emptyList()));
        }

        return filteredProblemsByCategory;
    }
    public static Map<String, List<ShowProblem>> getProblemsBySelectedStatus(Map<String, List<ShowProblem>> problemsByStatus, String selectedStatus) {
        Map<String, List<ShowProblem>> filteredProblemsByCategory = new HashMap<>();
        if (selectedStatus == null || selectedStatus.isEmpty() || selectedStatus.equals("")) {
            return problemsByStatus;
        }

        for (Map.Entry<String, List<ShowProblem>> entry : problemsByStatus.entrySet()) {
            List<ShowProblem> problems = entry.getValue();
            List<ShowProblem> filteredProblems = new ArrayList<>();

            for (ShowProblem problem : problems) {
                if (problem.getStatus().equals(selectedStatus)) {
                    filteredProblems.add(problem);
                }
            }

            if (!filteredProblems.isEmpty()) {
                filteredProblemsByCategory.put(entry.getKey(), filteredProblems);
            }
        }

        return filteredProblemsByCategory;
    }
    public List<String> getUniqueCategories() {
        List<Problem> allProblems = GetAll();
        return allProblems.stream()
                .map(Problem::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }
    public List<String> getUniqueStatuses() {
        List<Problem> allProblems = GetAll();
        return allProblems.stream()
                .map(Problem::getStatus)
                .distinct()
                .collect(Collectors.toList());
    }

}
