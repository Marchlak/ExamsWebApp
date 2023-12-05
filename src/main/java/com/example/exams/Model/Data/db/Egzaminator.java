package com.example.exams.Model.Data.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "egzaminator")
public class Egzaminator {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "examiner_id", nullable = false)
    private Integer examiner_id;

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

    @Column(name = "verification_status", nullable = false)
    private boolean verificationStatus;
}