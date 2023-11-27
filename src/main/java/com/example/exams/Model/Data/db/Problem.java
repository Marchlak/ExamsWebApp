package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "problem")
public class Problem {
    @Id
    @Column(name = "problemid", nullable = false)
    private Integer id;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_student_id")
    private Student studentStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egzaminator_egzaminator_id")
    private Egzaminator egzaminatorEgzaminator;

}