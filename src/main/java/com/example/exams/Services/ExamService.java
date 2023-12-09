package com.example.exams.Services;

import com.example.exams.Model.Data.ProperDataModels.ExamDTO;
import com.example.exams.Model.Data.ProperDataModels.OpenQuestionDTO;
import com.example.exams.Model.Data.db.Exam;
import com.example.exams.Model.Data.db.Openquestion;
import com.example.exams.Repositories.Db.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final SubjectService subjectService;
    private final EgzaminatorService egzaminatorService;
    private final OpenQuestionService openQuestionService;

    @Autowired
    public ExamService(ExamRepository examRepository, SubjectService subjectService, EgzaminatorService egzaminatorService, OpenQuestionService openQuestionService) {
        this.examRepository = examRepository;
        this.subjectService = subjectService;
        this.egzaminatorService = egzaminatorService;
        this.openQuestionService = openQuestionService;
    }

    public Exam AddExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam GetExam(int id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    public void updateExam(Exam updatedExam) {
        examRepository.save(updatedExam);
    }

    @Transactional
    public boolean deleteExam(Integer examId) {
        if (examRepository.existsById(examId)) {
            examRepository.deleteById(examId);
            return true;
        }
        return false;
    }
    public Exam AddExam(ExamDTO examDTO){
        Exam exam = new Exam();
        exam.setSubjectSubjectid(subjectService.GetOne(examDTO.getSubjectId()));
        exam.setEgzaminatorEgzaminator(egzaminatorService.Get(examDTO.getEgzamiantor()));
        exam.setDescription(examDTO.getDescription());
        exam.setStartdate(examDTO.getStartDate());
        exam.setStarttime(examDTO.getStartTime());
        exam.setEnddate(examDTO.getEndDate());
        exam.setEndtime(examDTO.getEndTime());
        Exam addedExam = examRepository.save(exam);
        for(OpenQuestionDTO openQuestionDTO : examDTO.getQuestions()){
            Openquestion openquestion = new Openquestion();
            openquestion.setExam(addedExam);
            openquestion.setScore(openQuestionDTO.getScore());
            openquestion.setContent(openQuestionDTO.getContent());
            openQuestionService.AddOpenQuestion(openquestion);
        }
        return addedExam;
    }
}
