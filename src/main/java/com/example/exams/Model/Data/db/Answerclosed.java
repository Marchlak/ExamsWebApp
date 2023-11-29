package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answerclosed")
public class Answerclosed {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "closedquestion_questionid", nullable = false)
    private Closedquestion closedquestionQuestionid;

}