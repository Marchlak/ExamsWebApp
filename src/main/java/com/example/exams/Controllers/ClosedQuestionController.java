package com.example.exams.Controllers;

import com.example.exams.Services.AnswerClosedService;
import com.example.exams.Services.ClosedQuestionService;
import com.example.exams.Services.LogsService;
import com.example.exams.Services.StudentClosedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClosedQuestionController {
@Autowired
ClosedQuestionService closedQuestionService;

@Autowired
StudentClosedAnswerService studentClosedAnswerService;

@Autowired
AnswerClosedService answerClosedService;

    @Autowired
    LogsService logsService;


    @PostMapping("/processClosedQuestionForm")
    public String processOpenQuestionForm(@RequestParam("action") String action) {
        String[] parts = action.split(":");
        String command = parts[0];
        Integer closeQuestionId = Integer.parseInt(parts[1]);
        if ("edit".equals(command)) {
            return "redirect:/editOpenQuestion/" + closeQuestionId;
        } else if ("delete".equals(command) && parts.length == 3) {
            Integer examId = Integer.parseInt(parts[2]);
            logsService.deleteClosedQuestion(closeQuestionId);
            studentClosedAnswerService.deleteStudentClosedAnswersByQuestionId(closeQuestionId);
            answerClosedService.deleteAnswersByQuestionId(closeQuestionId);
            closedQuestionService.deleteClosedQuestion(closeQuestionId);
            return "redirect:/showExamDetails/" + examId;
        }
        return "error";

    }
}
