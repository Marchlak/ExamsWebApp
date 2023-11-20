package com.example.exams.Model.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answeropen")
public class Answeropen {
    @Id
    @Column(name = "answerid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "openquestion_questionid", nullable = false)
    private Openquestion openquestionQuestionid;

}