package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "logstudentexam")
public class Logstudentexam {
    @Id
    @Column(name = "logstudentexamid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "Date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_examid")
    private Exam examExamid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_student_id")
    private Student studentStudent;

}