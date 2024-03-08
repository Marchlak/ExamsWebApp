package com.example.exams.Services;

import com.example.exams.Model.Data.db.Administrator;
import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Repositories.Db.AdministratorsEntityRepository;
import com.example.exams.Repositories.Db.ExaminerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AdministratorServiceTest {
    private final AdministratorsEntityRepository administratorsRepository = Mockito.mock(AdministratorsEntityRepository.class);
    private final AdministartorService administratorService = new AdministartorService(administratorsRepository);

    @Test
    public void testActivate() {
        Administrator administrator = new Administrator();
        administrator.setVerificationStatus(false);

        Mockito.when(administratorsRepository.findById(any(Integer.class))).thenReturn(Optional.of(administrator));

        administratorService.activate(1);

        verify(administratorsRepository, times(1)).save(administrator);
        assert(administrator.isVerificationStatus());
    }

    @Test
    public void testDeactivate() {
        Administrator administrator = new Administrator();
        administrator.setVerificationStatus(true);

        Mockito.when(administratorsRepository.findById(any(Integer.class))).thenReturn(Optional.of(administrator));

        administratorService.deactivate(1);

        verify(administratorsRepository, times(1)).save(administrator);
        assert(!administrator.isVerificationStatus());
    }
}
