package com.example.exams.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.exams.Model.Data.ProperDataModels.Login;
import com.example.exams.Services.LoginsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class Controller {

    @Autowired
    LoginsService loginsService;


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
}
