package com.example.exams.Model.Data.MN;

import com.example.exams.Model.Data.db.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "openquestion")
public class Openquestion {
    @Id
    @Column(name = "questionid", nullable = false)
    private Integer questionid;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "score")
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_subjectid", nullable = false)
    private Subject subject_subjectid;
}