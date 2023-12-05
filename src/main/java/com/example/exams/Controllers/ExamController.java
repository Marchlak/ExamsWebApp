package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Subject;
import com.example.exams.Services.EgzaminatorService;
import com.example.exams.Services.ExamService;
import com.example.exams.Services.SubjectService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    EgzaminatorService egzaminatorService;

    @PostMapping("/addExam/{egzaminator_egzaminator_id}")
    public ResponseEntity<String> UpdateOpenQuestion(@ModelAttribute Exam exam, @PathVariable Integer egzaminator_egzaminator_id, @RequestParam Integer subjectid){

        //Testy
        egzaminator_egzaminator_id = 1;

        Egzaminator egzaminator = egzaminatorService.Get(egzaminator_egzaminator_id);
        exam.setEgzaminatorEgzaminator(egzaminator);

        Subject subject = subjectService.Get(subjectid);
        exam.setSubjectSubjectid(subject);

        Exam addedExam = examService.AddExam(exam);
        return ResponseEntity.ok("Question updated successfully");
    }
}

