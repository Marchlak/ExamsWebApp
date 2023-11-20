package com.example.exams.Model.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @Column(name = "examid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "startdate")
    private LocalDate startdate;

    @Column(name = "enddate")
    private LocalDate enddate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_subjectid", nullable = false)
    private Subject subjectSubjectid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closedquestion_questionid")
    private Closedquestion closedquestionQuestionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "openquestion_questionid")
    private Openquestion openquestionQuestionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egzaminator_egzaminator_id")
    private Egzaminator egzaminatorEgzaminator;

}