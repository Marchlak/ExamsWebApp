package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "closedquestion")
public class Closedquestion {
    @Id
    @Column(name = "questionid", nullable = false)
    private Integer id;

    @Column(name = "answerclosedquestionid")
    private Integer answerclosedquestionid;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_subjectid", nullable = false)
    private Subject subjectSubjectid;

}