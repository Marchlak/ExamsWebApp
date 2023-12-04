package com.example.exams.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.exams.Services.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @DeleteMapping("/{examId}")
    public void deleteExam(@PathVariable Integer examId) {
        examService.deleteExam(examId);
    }
}

