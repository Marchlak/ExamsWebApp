package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Openquestion;
import com.example.exams.Services.OpenQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OpenQuestionController {
//    private final OpenQuestionService openQuestionService;
//
//    @Autowired
//    OpenQuestionController(OpenQuestionService openQuestionService){
//        this.openQuestionService = openQuestionService;
//    }

    @Autowired
    OpenQuestionService openQuestionService;

    @GetMapping("/editOpenQuestion/{questionid}")
    public ModelAndView EditOpenQuestion(@PathVariable Integer questionid){
        Openquestion Openquestion = openQuestionService.GetOpenQuestion(questionid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOpenQuestion");
        modelAndView.addObject("Openquestion", Openquestion);
        return modelAndView;
    }
    @PostMapping("/updateOpenQuestion/{questionid}")
    public ResponseEntity<String> UpdateOpenQuestion(@ModelAttribute Openquestion Openquestion, @PathVariable Integer questionid){
        Openquestion.setQuestionid(questionid);
        Openquestion updatedOpenQuestionOptional = openQuestionService.UpdateOpenQuestion(Openquestion);
        if(updatedOpenQuestionOptional != null){
            return ResponseEntity.ok("Question updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }
    @GetMapping("/createOpenQuestion")
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("openquestion", new Openquestion());
        modelAndView.setViewName("createOpenQuestion");
        return modelAndView;
    }

    @PostMapping("/createOpenQuestion")
    public void add(@ModelAttribute Openquestion openquestion)
    {
        openQuestionService.AddOpenQuestion(openquestion);
    }


    @GetMapping("/question")
    public ModelAndView questions() {
        ModelAndView modelAndView = new ModelAndView("question/list");
        modelAndView.addObject("questions", openQuestionService.getAll());
        modelAndView.setViewName("questionsView");
        return modelAndView;
    }


    @GetMapping("/deleteOpenQuestion/{questionid}")
    public ResponseEntity<String> deleteOpenQuestion(@PathVariable Integer questionid) {
        boolean deleted = openQuestionService.deleteOpenQuestion(questionid);
        if (deleted) {
            return ResponseEntity.ok("Question deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }

}
