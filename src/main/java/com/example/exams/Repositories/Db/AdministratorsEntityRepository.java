package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorsEntityRepository extends JpaRepository<Administrator, Integer> {
    Administrator findAdministratorByLogin(String login);
}