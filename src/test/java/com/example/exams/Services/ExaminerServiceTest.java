package com.example.exams.Services;

import com.example.exams.Model.Data.db.Examiner;
import com.example.exams.Repositories.Db.ExaminerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExaminerServiceTest {

    private final ExaminerRepository examinerRepository = Mockito.mock(ExaminerRepository.class);
    private final ExaminerService examinerService = new ExaminerService(examinerRepository);

    @Test
    public void testActivate() {
        Examiner examiner = new Examiner();
        examiner.setVerificationStatus(false);

        Mockito.when(examinerRepository.findById(any(Integer.class))).thenReturn(Optional.of(examiner));

        examinerService.activate(1);

        verify(examinerRepository, times(1)).save(examiner);
        assert(examiner.isVerificationStatus());
    }

    @Test
    public void testDeactivate() {
        Examiner examiner = new Examiner();
        examiner.setVerificationStatus(true);

        Mockito.when(examinerRepository.findById(any(Integer.class))).thenReturn(Optional.of(examiner));

        examinerService.deactivate(1);

        verify(examinerRepository, times(1)).save(examiner);
        assert(!examiner.isVerificationStatus());
    }
}