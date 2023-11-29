package com.example.exams.Model.Data.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "egzaminator")
public class Egzaminator {
    @Id
    @Column(name = "egzaminator_id", nullable = false)
    private Integer id;

    @Column(name = "accountid", nullable = false)
    private Integer accountid;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "lastname", length = 20)
    private String lastname;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "email", length = 20)
    private String email;

}