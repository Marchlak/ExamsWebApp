package com.example.exams.Services;

import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    @Autowired
    StudentsEntityRepository studentsRepository;

    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Student getStudentById(Integer studentId){
        return studentsRepository.findStudentByStudentId(studentId);
    }
}
