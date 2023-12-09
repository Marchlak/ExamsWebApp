package com.example.exams.Model.Data.ProperDataModels;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ExamResponseDTO {
    private Map<String, String> openAnswers;
    private Map<String, String[]> closedAnswers;
}