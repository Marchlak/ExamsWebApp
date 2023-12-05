package com.example.exams.Model.Data.ProperDataModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstname;

    private String lastname;

    private String login;

    private String password;

    private String email;
}