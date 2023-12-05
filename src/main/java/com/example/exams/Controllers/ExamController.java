package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Subject;
import com.example.exams.Services.EgzaminatorService;
import com.example.exams.Services.ExamService;
import com.example.exams.Services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    EgzaminatorService egzaminatorService;

    @PostMapping("/addExam/{egzaminator_egzaminator_id}")
    public String UpdateOpenQuestion(@ModelAttribute Exam exam, @PathVariable Integer egzaminator_egzaminator_id, @RequestParam Integer subjectid){

        //Testy
        egzaminator_egzaminator_id = 1;

        Egzaminator egzaminator = egzaminatorService.Get(egzaminator_egzaminator_id);
        exam.setEgzaminatorEgzaminator(egzaminator);

        Subject subject = subjectService.Get(subjectid);
        exam.setSubjectSubjectid(subject);

        Exam addedExam = examService.AddExam(exam);
        return "redirect:/exams";
    }

    @PostMapping("/editExamDetails/{examId}")
    public String editExamDetails(@PathVariable Integer examId, @ModelAttribute Exam exam, @RequestParam("subject") Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId.intValue());
        exam.setId(examId.intValue());
        exam.setSubjectSubjectid(subject);
        examService.updateExam(exam);
        return "redirect:/exams";
    }
    @GetMapping("/editExam/{examId}")
    public ModelAndView editExam(@PathVariable Integer examId, Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editExam");
        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
        return modelAndView;
    }
    @PostMapping("/processForm")
    public String processForm( @RequestParam("action") String action) {
        Integer examId = Integer.parseInt(action.substring(5));
        if (action.startsWith("edit:")) {
            return "redirect:/editExam/" + examId;
        }
        else if (action.startsWith("delete:")) {
            // Cos tu wstaw
            return "redirect:/deleteExam";
        }
        return "error";
    }
}

