package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsEntityRepository extends JpaRepository<Student, Integer> {
    Student findStudentByLogin(String login);
}
