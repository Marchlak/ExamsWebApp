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
@Table(name = "`group`")
public class Group {
    @Id
    @Column(name = "classid", nullable = false)
    private Integer id;

    @Column(name = "code", length = 10)
    private String code;

}