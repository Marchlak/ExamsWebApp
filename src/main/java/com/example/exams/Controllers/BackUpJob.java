package com.example.exams.Controllers;


import com.example.exams.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BackUpJob {
    @Autowired
    private ExamService examService;
    @Scheduled(cron = "* * * * * ?")
    public void performBackup() {
        System.out.println("Kopiazapasowazostałautworzona");
        examService.createBackup();
    }
}
