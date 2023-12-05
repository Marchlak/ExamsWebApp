package com.example.exams.Services;

import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Model.Data.ProperDataModels.User;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.AdministratorsEntityRepository;
import com.example.exams.Repositories.Db.ExaminersEntityRepository;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    AdministratorsEntityRepository administratorsEntityRepository;

    @Autowired
    ExaminersEntityRepository examinersEntityRepository;

    @Autowired
    StudentsEntityRepository studentsEntityRepository;

    public void addAdministratorToDB(Administrator administrator){
        administratorsEntityRepository.save(administrator);
    }
    public void addAExaminerToDB(Egzaminator egzaminator){
        examinersEntityRepository.save(egzaminator);
    }
    public void addAStudentToDB(Student student) {
        studentsEntityRepository.save(student);
    }
    public Administrator mapToAdministratorEntity(User user) {
        Administrator administrator = new Administrator();
        administrator.setFirstname(user.getFirstname());
        administrator.setLastname(user.getLastname());
        administrator.setLogin(user.getLogin());
        administrator.setPassword(user.getPassword());
        administrator.setEmail(user.getEmail());
        administrator.setVerificationStatus(false);
        return administrator;
    }
    public Egzaminator mapToExaminerEntity(User user) {
        Egzaminator egzaminator = new Egzaminator();
        egzaminator.setFirstname(user.getFirstname());
        egzaminator.setLastname(user.getLastname());
        egzaminator.setLogin(user.getLogin());
        egzaminator.setPassword(user.getPassword());
        egzaminator.setEmail(user.getEmail());
        egzaminator.setVerificationStatus(false);
        return egzaminator;
    }
    public Student mapToStudentEntity(User user) {
        Student student = new Student();
        student.setFirstname(user.getFirstname());
        student.setLastname(user.getLastname());
        student.setLogin(user.getLogin());
        student.setPassword(user.getPassword());
        student.setEmail(user.getEmail());
        return student;
    }
    public Administrator getAdministratorByEmail(String administratorEmail) {
        List<Administrator> administrators = administratorsEntityRepository.findAll();
        for (Administrator administrator : administrators) {
            if (administrator.getEmail().equals(administratorEmail)) {
                return administrator;
            }
        }
        return null;
    }
    public Egzaminator getExaminerByEmail(String examinerEmail) {
        List<Egzaminator> examiners = examinersEntityRepository.findAll();
        for (Egzaminator examiner : examiners) {
            if (examiner.getEmail().equals(examinerEmail)) {
                return examiner;
            }
        }
        return null;
    }
    public Student getStudentByEmail(String studentEmail) {
        List<Student> students = studentsEntityRepository.findAll();
        for (Student student : students) {
            if (student.getEmail().equals(studentEmail)) {
                return student;
            }
        }
        return null;
    }
    public String determinePermissions(String email) {
        if (email.endsWith("@student.pb.edu.pl")) {
            return "Student";
        } else if (email.endsWith("@examiner.pb.edu.pl")) {
            return "Examiner";
        } else if (email.endsWith("@administrator.pb.edu.pl")) {
            return "Administrator";
        } else {
            return "UnknownTypeOfUser";
        }
    }

}
