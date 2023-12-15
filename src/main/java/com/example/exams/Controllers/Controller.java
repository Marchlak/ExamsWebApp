package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Log;
import com.example.exams.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Model.Data.ProperDataModels.Login;
import com.example.exams.Model.Data.ProperDataModels.User;
import com.example.exams.Model.Data.db.Student;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    LoginsService loginsService;

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    UsersService usersService;
    @Autowired
    LogsService logsService;

    public Controller(ExamService examService)
    {
        this.examService = examService;
    }

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        model.addAttribute("login", new Login());
        return modelAndView;
    }

    @GetMapping("/logged")
    public ModelAndView logged(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logged");
        return modelAndView;
    }

    @GetMapping("/exams")
    public ModelAndView exams() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exams", examService.getAllExams());
        modelAndView.addObject("subjects", subjectService.GetAll());
        modelAndView.setViewName("showExams");
        return modelAndView;
    }
    @GetMapping("/logs")
    public ModelAndView showLogs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logs", logsService.getLogs());
        modelAndView.setViewName("showLogs");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        model.addAttribute("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView  register(@ModelAttribute("user") User user) {
        String result = usersService.determinePermissions(user.getEmail());

        switch (result) {
            case "Student":
                Student newStudent = usersService.mapToStudentEntity(user);
                usersService.addAStudentToDB(newStudent);
                return new ModelAndView("redirect:/login");
            case "Examiner":
                Examiner newExaminer = usersService.mapToExaminerEntity(user);
                usersService.addAExaminerToDB(newExaminer);
                return new ModelAndView("redirect:/login");
            case "Administrator":
                Administrator newAdministrator = usersService.mapToAdministratorEntity(user);
                usersService.addAdministratorToDB(newAdministrator);
                return new ModelAndView("redirect:/login");
            default:
                return new ModelAndView("register");
        }
    }

//    @GetMapping("/createQuestion")
//    public ModelAndView createQuestion() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("createQuestion");
//        return modelAndView;
//    }

//    @GetMapping("/showQuestion")
//    public ModelAndView showQuestion() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("questionsView");
//        return modelAndView;
//    }
}
