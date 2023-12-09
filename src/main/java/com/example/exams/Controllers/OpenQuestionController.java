package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.OpenQuestion;
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

    @GetMapping("/editOpenQuestion/{openQuestionId}")
    public ModelAndView EditOpenQuestion(@PathVariable Integer openQuestionId){
        OpenQuestion openQuestion = openQuestionService.GetOpenQuestion(openQuestionId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editOpenQuestion");
        modelAndView.addObject("openQuestion", openQuestion);
        return modelAndView;
    }

    @PostMapping("/updateOpenQuestion/{openQuestionId}")
    public ResponseEntity<String> UpdateOpenQuestion(@ModelAttribute OpenQuestion openQuestion, @PathVariable Integer openQuestionId){
        openQuestion.setOpenQuestionId(openQuestionId);
        OpenQuestion updatedOpenQuestionOptional = openQuestionService.UpdateOpenQuestion(openQuestion);
        if(updatedOpenQuestionOptional != null){
            return ResponseEntity.ok("Question updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }

    @GetMapping("/createOpenQuestion")
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("openQuestion", new OpenQuestion());
        modelAndView.setViewName("createOpenQuestion");
        return modelAndView;
    }

    @PostMapping("/createOpenQuestion")
    public void add(@ModelAttribute OpenQuestion openquestion)
    {
        openQuestionService.AddOpenQuestion(openquestion);
    }


    @GetMapping("/openQuestions")
    public ModelAndView getAllOpenQuestions() {
        ModelAndView modelAndView = new ModelAndView("openQuestions/list");
        modelAndView.addObject("questions", openQuestionService.getAll());
        modelAndView.setViewName("questionsView");
        return modelAndView;
    }

    @GetMapping("/deleteOpenQuestion/{openQuestionId}")
    public ResponseEntity<String> deleteOpenQuestion(@PathVariable Integer openQuestionId) {
        boolean deleted = openQuestionService.deleteOpenQuestion(openQuestionId);
        if (deleted) {
            return ResponseEntity.ok("Question deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
    }

    @PostMapping("/processOpenQuestionForm")
    public String processOpenQuestionForm(@RequestParam("action") String action) {

        Integer openQuestionId = Integer.parseInt(action.substring(action.indexOf(':') + 1));
        if (action.startsWith("edit:")) {
            return "redirect:/editOpenQuestion/" + openQuestionId;
        } else if (action.startsWith("delete:")) {
//            return "redirect:/confirmOpenQuestionDeletion/" + openQuestionId;
            return "error";
        }
        return "error";
    }

}
