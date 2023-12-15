package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "closedquestion")
public class Closedquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closed_question_id", nullable = false)
    private Integer id;

    /*@Column(name = "answerclosedquestionid")
    private Integer answerclosedquestionid;*/

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "subject_subjectid", nullable = true)
    private Subject subject_subjectid;*/

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "exam_id", nullable = true)
    private Exam exam;

  //  @OneToMany(mappedBy = "closedquestionQuestionid", cascade = CascadeType.ALL, orphanRemoval = true)
 //   private List<Answerclosed> answers = new ArrayList<>();

    public Closedquestion(Integer closed_question_id, String content, Integer score, Exam exam) {
        this.id = closed_question_id;
        //this.answerclosedquestionid = answerclosedquestionid;
        this.content = content;
        this.score = score;
        this.exam = exam;
    }
}