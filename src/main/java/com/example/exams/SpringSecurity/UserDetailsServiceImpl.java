package com.example.exams.SpringSecurity;

import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.Egzaminator;
import com.example.exams.Model.Data.ProperDataModels.Login;
import com.example.exams.Repositories.Db.AdministratorsEntityRepository;
import com.example.exams.Repositories.Db.ExaminersEntityRepository;
import com.example.exams.Repositories.Db.StudentsEntityRepository;
import com.example.exams.Services.LoginsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AdministratorsEntityRepository administratorsEntityRepository;

    @Autowired
    ExaminersEntityRepository examinersEntityRepository;

    @Autowired
    StudentsEntityRepository studentsEntityRepository;

    @Autowired
    LoginsService loginsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator administrator = administratorsEntityRepository.findAdministratorByLogin(username);
        Egzaminator examiner = null;
        Student student = null;

        if (administrator == null) {
            examiner = examinersEntityRepository.findEgzaminatorByLogin(username);
            if (examiner == null) {
                student = studentsEntityRepository.findStudentByLogin(username);
            }
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password = request.getParameter("password");

        loginsService.getAllLogins().clear();
        loginsService.getAllLogins().add(new Login(username, password));

        UserDetails userDetails = null;

        if (administrator != null) {
            userDetails = buildUserDetails(administrator.getLogin(), administrator.getPassword(), Collections.singleton("ADMIN"));
        } else if (examiner != null) {
            userDetails = buildUserDetails(examiner.getLogin(), examiner.getPassword(), Collections.singleton("EXAMINER"));
        } else if (student != null) {
            userDetails = buildUserDetails(student.getLogin(), student.getPassword(), Collections.singleton("STUDENT"));
        }

        HttpSession session = request.getSession(false);
        if (session != null && !session.isNew() && userDetails != null) {
            session.setAttribute("UsersEntity", userDetails);
        }

        if (userDetails == null) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        return userDetails;
    }

    private UserDetails buildUserDetails(String username, String password, Set<String> roles) {
        return new org.springframework.security.core.userdetails.User(
                username,
                password,
                roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toSet())
        );
    }
}
