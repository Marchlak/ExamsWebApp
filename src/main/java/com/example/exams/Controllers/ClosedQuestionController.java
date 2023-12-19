package com.example.exams.Controllers;

import com.example.exams.Model.Data.ProperDataModels.AnswerForm;
import com.example.exams.Model.Data.db.Answerclosed;
import com.example.exams.Model.Data.db.Closedquestion;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClosedQuestionController {
@Autowired
ClosedQuestionService closedQuestionService;

@Autowired
StudentClosedAnswerService studentClosedAnswerService;

@Autowired
AnswerClosedService answerClosedService;

@Autowired
ExamService examService;

@Autowired
LogsService logsService;


    @PostMapping("/processClosedQuestionForm")
    public String processOpenQuestionForm(@RequestParam("action") String action) {
        String[] parts = action.split(":");
        String command = parts[0];
        Integer closeQuestionId = Integer.parseInt(parts[1]);
        if ("edit".equals(command)) {
            return "redirect:/editClosedQuestion/" + closeQuestionId;
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

    @GetMapping("/editClosedQuestion/{id}")
    public ModelAndView editClosedQuestion(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Closedquestion question = closedQuestionService.getClosedQuestionById(id);
        List<Answerclosed> answers = answerClosedService.getAllByQuestionId(id);

        AnswerForm answerForm = new AnswerForm();
        answerForm.setQuestion(question);
        answerForm.setAnswers(answers);

        modelAndView.addObject("answerForm", answerForm);
        modelAndView.setViewName("editClosedQuestion");
        return modelAndView;
    }


    @PostMapping("/editQuestion")
    public String saveQuestionChanges(@ModelAttribute("answerForm")  AnswerForm answerForm, Integer examId, BindingResult result, Model model) {
       //
        Closedquestion question = answerForm.getQuestion();
        System.out.println(question.getContent());
        System.out.println(question.getId());
        System.out.println("tu");
       answerForm.printAnswersContent();
        if (result.hasErrors()) {
            return "editClosedQuestion";
        }
        if (examId != null) {
            Exam exam = examService.GetExam(examId);
            question.setExam(exam);
        }


        closedQuestionService.save(question);

        return "redirect:/exams";
    }
}
