package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "problem")
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "problemid", nullable = false)
    private Integer id;

    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problems_student_id")
    private Student problemsStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problems_examiner_id")
    private Examiner problemsExaminer;

    @Column(name = "category", length = 20)
    private String category;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "status", length = 20)
    private String status = "Nowy";
}