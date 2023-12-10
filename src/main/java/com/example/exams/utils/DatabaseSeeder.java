package com.example.exams.utils;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AdministratorsEntityRepository administratorRepository;
    private final SubjectRepository subjectRepository;
    private final ExaminerRepository examinerRepository;
    private final ExamRepository examRepository;
    private final OpenQuestionRepository openQuestionRepository;
    private final StudentsEntityRepository studentsEntityRepository;


    public DatabaseSeeder(AdministratorsEntityRepository administratorRepository, ExamRepository examRepository, SubjectRepository subjectRepository, ExaminerRepository examinersRepository, OpenQuestionRepository openQuestionRepository, StudentsEntityRepository studentsEntityRepository) {
        this.administratorRepository = administratorRepository;
        this.examinerRepository = examinersRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.studentsEntityRepository = studentsEntityRepository;
    }


    @Override
    public void run(String... args) {
        administratorRepository.save(new Administrator(1, "Szast", "Prast", "wipb", "pebegate", "wi@pb.edu.pl", true));
        administratorRepository.save(new Administrator(2, "Dziekanat", "WI PB", "dziekanatwi", "dziekanatwi", "dziekanatwi@pb.edu.pl", true));

        subjectRepository.save(new Subject(1, "Matematyka"));
        subjectRepository.save(new Subject(2, "Angielski"));
        subjectRepository.save(new Subject(3, "Polski"));
        subjectRepository.save(new Subject(4, "Historia"));
        subjectRepository.save(new Subject(5, "Niemiecki"));

        examinerRepository.save(new Examiner(1, "Slawomir", "Golibroda", "golibrodka", "pebe1234", "s.golibrodai@pb.edu.pl", true));
        examinerRepository.save(new Examiner(2, "Dorota", "Cuda", "wedliny", "cuda2115", "cudawedliny@pb.edu.pl", true));
        examinerRepository.save(new Examiner(3, "Marta", "Korsarz", "pbenjoyer", "szastprast", "smoczyca@pb.edu.pl", false));

        examRepository.save(new Exam(1, "Projektowanie części w SOLIDWORKS", LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now().plusHours(1), subjectRepository.findById(1).get()));
        examRepository.save(new Exam(2, "Retusz zdjęć rektor PB w Photoshop", LocalDate.now().minusDays(2), LocalTime.now(), LocalDate.now(), LocalTime.now().plusHours(1), subjectRepository.findById(1).get()));
        examRepository.save(new Exam(3, "Jak zrobić sprawozdanie w MS Paint", LocalDate.now().minusDays(2), LocalTime.now(), LocalDate.now(), LocalTime.now().plusHours(1), subjectRepository.findById(3).get()));

        openQuestionRepository.save(new OpenQuestion(1, "Ile to 5+5?", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(2, "Ile to 30*3", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(3, "Ile to 19+3?", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));

    }
}
