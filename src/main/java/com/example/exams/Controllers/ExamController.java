package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping("/createExam/{egzaminator_egzaminator_id}")
    public ModelAndView AddExam(@PathVariable Integer egzaminator_egzaminator_id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addExam");
        modelAndView.addObject("egzaminator_egzaminator_id", egzaminator_egzaminator_id);
        return modelAndView;
    }
    @PostMapping("/addExam/{egzaminator_egzaminator_id}")
    public ResponseEntity<String> UpdateOpenQuestion(@ModelAttribute Exam exam, @PathVariable Integer egzaminator_egzaminator_id){
        Egzaminator egzaminator = new Egzaminator();
        egzaminator.setId(egzaminator_egzaminator_id);
        exam.setEgzaminatorEgzaminator(egzaminator);
        Exam addedExam = examService.AddExam(exam);
        return ResponseEntity.ok("Question updated successfully");
    }
}
