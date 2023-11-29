package com.example.exams;

import com.example.exams.Model.Data.ProperDataModels.Login;
import com.example.exams.Repositories.OpenquestionRepository;
import com.example.exams.Services.LoginsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    @Autowired
    LoginsService loginsService;

    @Autowired
    OpenquestionRepository openQuestionRepository;


    @GetMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        model.addAttribute("login", new Login());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @GetMapping("/createQuestion")
    public ModelAndView createQuestion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createQuestion");
        return modelAndView;
    }

    @RequestMapping(value = "question", method = RequestMethod.GET)
    public ModelAndView questions() {
        ModelAndView modelAndView = new ModelAndView("question/list");
        modelAndView.addObject("questions", openQuestionRepository.findAll());
        modelAndView.setViewName("questionsView");
        return modelAndView;
    }
}
