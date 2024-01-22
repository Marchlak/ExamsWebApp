package com.example.exams.Services;

import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Repositories.Db.AdministratorsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministartorService {
    @Autowired
    AdministratorsEntityRepository administratorsEntityRepository;

    public Administrator getAdminById(Integer adminId){
        Optional <Administrator> admin = administratorsEntityRepository.findById(adminId);
        return admin.orElse(null);
    }

    public Administrator getAdminByLogin(String login) {
        return administratorsEntityRepository.findAdministratorByLogin(login);
    }
}
