package com.example.exams.Model.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "openquestion")
public class Openquestion {
    @Id
    @Column(name = "questionid", nullable = false)
    private Integer id;

    @Column(name = "answeropenquestionid")
    private Integer answeropenquestionid;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_subjectid", nullable = false)
    private Subject subjectSubjectid;

}