package com.example.exams.Model.Data.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @Column(name = "logsid", nullable = false)
    private Integer id;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "Date")
    private LocalDate date;

}