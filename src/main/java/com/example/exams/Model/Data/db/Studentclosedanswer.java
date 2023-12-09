package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "studentclosedanswer")
public class Studentclosedanswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid_1", nullable = false)
    private Integer id;

    @Column(name = "correctness", nullable = false)
    private Boolean correctness;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "answerclosed_answerid", nullable = false)
    private Answerclosed answerclosedAnswerid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closedquestion_questionid")
    private Closedquestion closedquestionQuestionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_student_id")
    private Student studentStudent;

}