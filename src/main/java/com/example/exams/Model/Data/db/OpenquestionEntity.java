package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "openquestion")
public class OpenquestionEntity {
    @Id
    @Column(name = "questionid", nullable = false)
    private Integer id;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

}