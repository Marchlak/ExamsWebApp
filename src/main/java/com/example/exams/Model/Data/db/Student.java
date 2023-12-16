package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Group> groups;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Exam> exams;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "email", length = 40)
    private String email;

    public Student() {}

    public Student(Integer studentId, String firstname, String lastname, String login, String password, String email) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}