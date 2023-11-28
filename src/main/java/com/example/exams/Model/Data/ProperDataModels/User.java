package com.example.exams.Model.Data.ProperDataModels;

import lombok.Data;

@Data
public class User {
    private Integer accountid;

    private String firstname;

    private String lastname;

    private String login;

    private String password;

    private String email;
}