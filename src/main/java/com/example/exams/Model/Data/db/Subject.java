package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subjectid", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;


}