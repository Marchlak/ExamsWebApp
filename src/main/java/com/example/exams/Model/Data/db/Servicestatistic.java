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
@Table(name = "servicestatistics")
public class Servicestatistic {
    @Id
    @Column(name = "statisticsID", nullable = false)
    private Integer id;

    @Column(name = "visitorscount")
    private Integer visitorscount;

    @Column(name = "examscount")
    private Integer examscount;

    @Column(name = "studentscount")
    private Integer studentscount;

    @Column(name = "examinatorscount")
    private Integer examinatorscount;

    @Column(name = "startdate")
    private LocalDate startdate;

}