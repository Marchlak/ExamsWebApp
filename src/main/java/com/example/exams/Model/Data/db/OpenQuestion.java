package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "openquestion")
public class OpenQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_question_id", nullable = false)
    private Integer openQuestionId;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "subject_subjectid", nullable = true)
    private Subject questionSubject;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "exam_id", nullable = true)
    private Exam exam;

    public OpenQuestion(Integer openQuestionId, String content, Integer score, Subject questionSubject, Exam exam) {
        this.openQuestionId = openQuestionId;
        this.content = content;
        this.score = score;
        this.questionSubject = questionSubject;
        this.exam = exam;
    }
}