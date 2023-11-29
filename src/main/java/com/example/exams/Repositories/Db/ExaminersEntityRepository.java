package com.example.exams.Repositories.Db;

import com.example.exams.Model.Data.db.Egzaminator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminersEntityRepository extends JpaRepository<Egzaminator, Integer> {
    Egzaminator findEgzaminatorByLogin(String login);
}
