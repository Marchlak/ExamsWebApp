package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "studentopenanswer")
public class Studentopenanswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Column(name = "score")
    private Integer score;


    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "openquestion_questionid")
    private OpenQuestion openquestionQuestionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_student_id")
    private Student studentStudent;

}