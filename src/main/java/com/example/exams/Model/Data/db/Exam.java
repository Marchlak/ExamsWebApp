package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "starttime")
    private LocalTime starttime;

    @Column(name = "enddate")
    private LocalDate enddate;

    @Column(name = "endtime")
    private LocalTime endtime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_subjectid", nullable = false)
    private Subject subjectSubjectid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egzaminator_egzaminator_id")
    private Egzaminator egzaminatorEgzaminator;

}