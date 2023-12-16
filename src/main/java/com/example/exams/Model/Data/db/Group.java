package com.example.exams.Model.Data.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @Column(name = "classid", nullable = false)
    private Integer id;

    @Column(name = "code", length = 10)
    private String code;

    @ManyToMany
    @JoinTable(
            name = "student_group",
            joinColumns = @JoinColumn(name = "group_classid"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Group(){}
    public Group(Integer group_id, String code, List<Student> students) {
        this.id = group_id;
        this.code = code;
        this.students = students;
    }
}