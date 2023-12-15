package com.example.exams.Controllers;

import com.example.exams.Model.Data.ProperDataModels.ShowProblem;
import com.example.exams.Model.Data.db.Problem;
import com.example.exams.Services.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ProblemController {

    ProblemService problemService;

    @Autowired
    ProblemController(ProblemService problemService){
        this.problemService = problemService;
    }

    @GetMapping("/reportProblem")
    public ModelAndView reportProblem(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("problemReporting");
        List<String> categories = new ArrayList<>();
        categories.add("Category 1");
        categories.add("Category 2");
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
    @PostMapping("/addProblem")
    public ModelAndView addProblem(@RequestParam("category") String category,
                                   @RequestParam("description") String description,
                                   @RequestParam("image") MultipartFile imageFile) throws IOException {

        Problem problem = new Problem();
        problem.setDescription(description);
        problem.setCategory(category);
        byte[] image = imageFile.getBytes();
        problem.setPhoto(image);

        UserDetails user = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (UserDetails) session.getAttribute("UsersEntity");
        }
        String userName = user.getUsername();
        problem.setUsername(userName);

        problemService.AddOne(problem);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("problemReporting");
        return modelAndView;
    }
    @GetMapping("/showProblems")
    public ModelAndView showProblems(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showProblems");
        List<Problem> problems = problemService.GetAll();
        List<ShowProblem> showProblemList = new ArrayList<>();
        for(Problem problem : problems){
            byte[] photoBytes = problem.getPhoto();
            String base64Encoded = Base64.getEncoder().encodeToString(photoBytes);

            ShowProblem showProblem = new ShowProblem();
            showProblem.setDescription(problem.getDescription());
            showProblem.setPhoto(base64Encoded);
            showProblem.setCategory(problem.getCategory());
            showProblem.setUsername(problem.getUsername());
            showProblemList.add(showProblem);
        }

        modelAndView.addObject("problems",showProblemList);
        return modelAndView;
    }

}
