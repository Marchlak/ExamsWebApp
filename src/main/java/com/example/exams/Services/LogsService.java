package com.example.exams.Services;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class LogsService {
    @Autowired
    LogRepository logRepository;
    @Autowired
    UsersService usersService;
    @Autowired
    ExamService examService;
    @Autowired
    OpenQuestionService openQuestionService;

    public LogsService(LogRepository logRepository, UsersService usersService,ExamService examService, OpenQuestionService openQuestionService){
        this.logRepository = logRepository;
        this.usersService = usersService;
        this.examService = examService;
        this.openQuestionService = openQuestionService;
    }
    public List<Log> getLogs(){
        return logRepository.findAll();
    }
    public void addLog(Log log){
        logRepository.save(log);
    }

    public void updateOpenQuestion(OpenQuestion changedOpenQuestion, int openquestionId){
        OpenQuestion currentOpenQuestion = openQuestionService.GetOpenQuestion(openquestionId);
        String s="Zmiany w otwartym pytaniu o id: "+openquestionId+" zostały dokonane przez "+whoIsLogged()+". "+"Zmiana nastąpiła w: ";
        int length = s.length();
        if(!changedOpenQuestion.getContent().equals(currentOpenQuestion.getContent())){
            s= s+ "Treści pytania - "+currentOpenQuestion.getContent()+" --> "+changedOpenQuestion.getContent();
        }
        if(changedOpenQuestion.getScore() != currentOpenQuestion.getScore()){
            if(s.length()!= length) s=s+", ";
            s= s+ "Punktacji pytania - "+currentOpenQuestion.getScore()+" --> "+changedOpenQuestion.getScore();
        }
        if(length != s.length()) addLog(new Log(s));
    }
    public void deleteOpenQuestion(int openQuestionId){
        String s= "Otwarte pytanie o id: "+openQuestionId+" zostało usunięte przez "+whoIsLogged();
        addLog(new Log(s));
    }


    public void updateExam(Exam exam,String s){
        s= "Zmiany w egzaminie o id: "+exam.getId()+" zostały dokonane przez "+whoIsLogged()+". "+s;
        addLog(new Log(s));
    }

    public void deleteExam(int examId){
        Exam exam = examService.GetExam(examId);
        String s= "Egzamin o id: "+exam.getId()+" został usunięty przez "+whoIsLogged();
        if(s!="") addLog(new Log(s));

    }
    public String whoIsLogged(){
        String s="";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession(false);
        UserDetails user = null;
        if (session != null) {

            user = (UserDetails) session.getAttribute("UsersEntity");
        }
        Examiner examiner = usersService.getExaminerByLoginAndPassword(user.getUsername());
        Administrator administrator = usersService.getAdministratorByLogin(user.getUsername());
        if(examiner != null) s= "egzaminatora: "+examiner.getFirstname()+" "+examiner.getLastname();
        if(administrator != null ) s= "administratora: "+administrator.getFirstname()+" "+administrator.getLastname();
        return s;
    }
}
