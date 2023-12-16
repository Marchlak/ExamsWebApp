package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "logstudentexam")
public class Logstudentexam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logstudentexamid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Time")
    private LocalTime time;

    @Column(name = "score_result")
    private Integer scoreresult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_examid")
    private Exam examExamid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_studentid")
    private Student studentStudent;

    public void addPoints(int points) {
        if (this.scoreresult == null) {
            this.scoreresult = 0;
        }
        this.scoreresult += points;
    }



}