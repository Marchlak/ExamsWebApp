package com.example.exams;

import com.example.exams.Model.Data.Openquestion;
import com.example.exams.Repositories.OpenquestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/createQuestion")
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createQuestion");
        return modelAndView;
    }

    @GetMapping("/questions")
        public List<Openquestion> getAll(){
        return openquestionRepository.getAll();
    }
    @PostMapping("/questions/create")
    public int add(@RequestBody List<Openquestion> openquestions)
    {
        return openquestionRepository.add(openquestions);
    }

}
