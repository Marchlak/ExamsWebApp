package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    EgzaminatorService egzaminatorService;

    @Autowired
    OpenQuestionService openQuestionService;

    @Autowired
    ClosedQuestionService closedQuestionService;

    @Autowired
    AnswerClosedService answerClosedService;


    @PostMapping("/addExam/{egzaminator_egzaminator_id}")
    public String UpdateOpenQuestion(@ModelAttribute Exam exam, @PathVariable Integer egzaminator_egzaminator_id, @RequestParam Integer subjectid) {

        //Testy
        egzaminator_egzaminator_id = 1;

        Egzaminator egzaminator = egzaminatorService.Get(egzaminator_egzaminator_id);
        exam.setEgzaminatorEgzaminator(egzaminator);

        Subject subject = subjectService.Get(subjectid);
        exam.setSubjectSubjectid(subject);

        Exam addedExam = examService.AddExam(exam);
        return "redirect:/exams";
    }

    @PostMapping("/editExamDetails/{examId}")
    public String editExamDetails(@PathVariable Integer examId, @ModelAttribute Exam exam, @RequestParam("subject") Integer subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId.intValue());
        exam.setId(examId.intValue());
        exam.setSubjectSubjectid(subject);
        examService.updateExam(exam);
        return "redirect:/exams";
    }

    @GetMapping("/editExam/{examId}")
    public ModelAndView editExam(@PathVariable Integer examId, Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editExam");
        modelAndView.addObject("subjects", subjects);
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
        return modelAndView;
    }

    @GetMapping("/confirmExamDeletion/{examId}")
    public ModelAndView deleteExam(@PathVariable Integer examId, Model model) {
//        boolean deleted = examService.deleteExam(examId);
//        if (deleted) {
//            return ResponseEntity.ok("Exam deleted successfully");
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found");
        Exam exam = examService.GetExam(examId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deleteExam");
        modelAndView.addObject("exam", exam);
        model.addAttribute("examId", examId);
//        examService.deleteExam(examId);
        return modelAndView;
    }

    @PostMapping("/deleteExam/{examId}")
    public ResponseEntity<String> deleteExam(@RequestParam Integer examId) {
        boolean deleted = examService.deleteExam(examId);
        if (deleted) {
            return ResponseEntity.ok("Exam deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found");
    }

    @GetMapping("/solveExam/{examId}")
    public ModelAndView getExamToSolve(@PathVariable String examId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("solveExam");

        Exam exam = examService.GetExam(Integer.parseInt(examId));
        LocalDate startdate = exam.getStartdate();
        LocalTime starttime = exam.getStarttime();

        if (LocalTime.now().isAfter(starttime) && LocalDate.now().isAfter(startdate))
            modelAndView.addObject("canSolve", true);
        else
            modelAndView.addObject("canSolve", false);

        modelAndView.addObject("exam", examService.GetExam(Integer.parseInt(examId)));
        modelAndView.addObject("listOpenQuestions", openQuestionService.getAllByExamId(Integer.parseInt(examId)));

        List<Closedquestion> listClosedQuestions = closedQuestionService.getAllByExamId(Integer.parseInt(examId));
        List<List<Answerclosed>> closedAnswers = new ArrayList<>();


        for (int i = 0; i < listClosedQuestions.size(); i++)
            closedAnswers.add(answerClosedService.getAllByQuestionId(listClosedQuestions.get(i).getId()));

        modelAndView.addObject("listClosedQuestions", closedQuestionService.getAllByExamId(Integer.parseInt(examId)));
        modelAndView.addObject("closedAnswers", closedAnswers);

        return modelAndView;
    }

    @PostMapping("/saveResolvedExam")
    public ModelAndView saveExam(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showExams");

        UserDetails user;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            modelAndView.addObject("UsersEntity", session.getAttribute("UsersEntity"));
            user = (UserDetails) session.getAttribute("UsersEntity");
        }

        return modelAndView;
    }

    @PostMapping("/processForm")
    public String processForm(@RequestParam("action") String action) {

        Integer examId = Integer.parseInt(action.substring(action.indexOf(':') + 1));
        if (action.startsWith("edit:")) {
            return "redirect:/editExam/" + examId;
        } else if (action.startsWith("delete:")) {
            return "redirect:/confirmExamDeletion/" + examId;
        } else if (action.startsWith("solveExam:")) {
            return "redirect:/solveExam/" + examId;
        }
        return "error";
    }
}

