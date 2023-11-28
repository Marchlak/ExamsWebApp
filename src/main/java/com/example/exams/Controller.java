package com.example.exams;

import com.example.exams.Model.Data.Openquestion;
import com.example.exams.Repositories.OpenquestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    OpenquestionRepository openquestionRepository;
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
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
        openquestionRepository.add(openquestion);
    }

    @GetMapping("/questions")
        public List<Openquestion> getAll(){
        return openquestionRepository.getAll();
    }
    /*@PostMapping("/createOpenQuestions")
    public int add(@RequestBody List<Openquestion> openquestions)
    {
        return openquestionRepository.add(openquestions);
    }*/

}
