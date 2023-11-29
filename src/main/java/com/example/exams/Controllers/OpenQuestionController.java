package com.example.exams.Controllers;

import com.example.exams.Model.Data.MN.Openquestion;
import com.example.exams.Services.OpenQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OpenQuestionController {
    private final OpenQuestionService openQuestionService;

    @Autowired
    OpenQuestionController(OpenQuestionService openQuestionService){
        this.openQuestionService = openQuestionService;
    }

    @GetMapping("/editOpenQuestion/{questionid}")
    public ModelAndView EditOpenQuestion(@PathVariable Integer questionid){
        Openquestion Openquestion = openQuestionService.GetOpenQuestion(questionid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOpenQuestion");
        modelAndView.addObject("Openquestion", Openquestion);
        return modelAndView;
    }
    @PostMapping("/updateOpenQuestion")
    public ResponseEntity<String> UpdateOpenQuestion(@ModelAttribute Openquestion Openquestion){
        Openquestion updatedOpenQuestionOptional = openQuestionService.UpdateOpenQuestion(Openquestion);
        if(updatedOpenQuestionOptional != null){
            return ResponseEntity.ok("Question updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }
}
