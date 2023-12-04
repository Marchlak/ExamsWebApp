package com.example.exams.Controllers;

import com.example.exams.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.exams.Model.Data.ProperDataModels.Login;
import com.example.exams.Services.LoginsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
@RestController
public class Controller {

    @Autowired
    LoginsService loginsService;

    @Autowired
    private ExamService examService;


    @GetMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        model.addAttribute("login", new Login());
        return modelAndView;
    }

    @GetMapping("/exams")
    public ModelAndView exams() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exams", examService.getAllExams());
        modelAndView.setViewName("showExams");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
