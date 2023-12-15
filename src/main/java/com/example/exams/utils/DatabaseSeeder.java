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
    private final ClosedQuestionRepository closedQuestionRepository;
    private final AnswerClosedRepository answerClosedRepository;
    private final StudentsEntityRepository studentsEntityRepository;
    private final ProblemRepository problemRepository;


    public DatabaseSeeder(AdministratorsEntityRepository administratorRepository, ExamRepository examRepository, SubjectRepository subjectRepository, ExaminerRepository examinersRepository, OpenQuestionRepository openQuestionRepository, ClosedQuestionRepository closedQuestionRepository, AnswerClosedRepository answerClosedRepository, StudentsEntityRepository studentsEntityRepository, ProblemRepository problemRepository) {
        this.administratorRepository = administratorRepository;
        this.examinerRepository = examinersRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerClosedRepository = answerClosedRepository;
        this.studentsEntityRepository = studentsEntityRepository;
        this.problemRepository = problemRepository;
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

        examRepository.save(new Exam(1, "Projektowanie części w SOLIDWORKS", LocalDate.now(), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(1).get()));
        examRepository.save(new Exam(2, "Retusz zdjęć rektor PB w Photoshop", LocalDate.now().minusDays(2), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(1).get()));
        examRepository.save(new Exam(3, "Jak zrobić sprawozdanie w MS Paint", LocalDate.now().minusDays(2), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(3).get()));

        openQuestionRepository.save(new OpenQuestion(1, "Ile to 5+5?", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(2, "Ile to 30*3", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(3, "Ile to 19+3?", 10, subjectRepository.getReferenceById(1), examRepository.getReferenceById(1)));

        closedQuestionRepository.save(new Closedquestion(1, 1, "ile to jest 10*10", 1, subjectRepository.findById(1).get(), examRepository.getReferenceById(1) ));
        closedQuestionRepository.save(new Closedquestion(2, 2, "ile to jest 11*11", 1, subjectRepository.findById(1).get(), examRepository.getReferenceById(1) ));
        closedQuestionRepository.save(new Closedquestion(3, 3, "ile to jest 12*12", 1, subjectRepository.findById(1).get(), examRepository.getReferenceById(1) ));

        answerClosedRepository.save(new Answerclosed(1, "to jest moze 101?", false, closedQuestionRepository.findById(1).get()));
        answerClosedRepository.save(new Answerclosed(2, "to jest moze 102?", false, closedQuestionRepository.findById(1).get()));
        answerClosedRepository.save(new Answerclosed(3, "to jest moze 2003?", false, closedQuestionRepository.findById(1).get()));
        answerClosedRepository.save(new Answerclosed(4, "to jest moze 100?", true, closedQuestionRepository.findById(1).get()));


        answerClosedRepository.save(new Answerclosed(5, "to jest moze 101?", false, closedQuestionRepository.findById(2).get()));
        answerClosedRepository.save(new Answerclosed(6, "to jest moze 121?", true, closedQuestionRepository.findById(2).get()));
        answerClosedRepository.save(new Answerclosed(7, "to jest moze 2003?", false, closedQuestionRepository.findById(2).get()));
        answerClosedRepository.save(new Answerclosed(8, "to jest moze 100?", false, closedQuestionRepository.findById(2).get()));


        answerClosedRepository.save(new Answerclosed(9, "to jest moze 101?", false, closedQuestionRepository.findById(3).get()));
        answerClosedRepository.save(new Answerclosed(10, "to jest moze 102?", false, closedQuestionRepository.findById(3).get()));
        answerClosedRepository.save(new Answerclosed(11, "to jest moze 144?", true, closedQuestionRepository.findById(3).get()));
        answerClosedRepository.save(new Answerclosed(12, "to jest moze 100?", false, closedQuestionRepository.findById(3).get()));


    }
}
