package com.example.exams.utils;

import com.example.exams.Model.Data.db.*;
import com.example.exams.Repositories.Db.*;
import com.example.exams.Services.GroupsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final AdministratorsEntityRepository administratorRepository;
    private final SubjectRepository subjectRepository;
    private final ExaminerRepository examinerRepository;
    private final ExamRepository examRepository;
    private final OpenQuestionRepository openQuestionRepository;
    private final ClosedQuestionRepository closedQuestionRepository;
    private final AnswerClosedRepository answerClosedRepository;

    private final StudentsEntityRepository studentsRepository;
    private final ProblemRepository problemRepository;
    private final GroupEntityRepository groupRepository;
    private final ServicestatisticRepository servicestatisticRepository;

    public DatabaseSeeder(AdministratorsEntityRepository administratorRepository, ExamRepository examRepository, SubjectRepository subjectRepository, ExaminerRepository examinersRepository, OpenQuestionRepository openQuestionRepository, ClosedQuestionRepository closedQuestionRepository, AnswerClosedRepository answerClosedRepository, StudentsEntityRepository studentsEntityRepository, ProblemRepository problemRepository, GroupEntityRepository groupRepository, ServicestatisticRepository servicestatisticRepository) {
        this.administratorRepository = administratorRepository;
        this.examinerRepository = examinersRepository;
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.answerClosedRepository = answerClosedRepository;
        this.studentsRepository = studentsEntityRepository;
        this.problemRepository = problemRepository;
        this.groupRepository = groupRepository;
        this.servicestatisticRepository = servicestatisticRepository;
    }

    @Override
    public void run(String... args) {
        administratorRepository.save(new Administrator(1, "Szast", "Prast", "wipb", "pebegate", "wi@pb.edu.pl", true));
        administratorRepository.save(new Administrator(2, "Wojciech", "WI PB", "wojtek", "1", "wojtek@pb.edu.pl", true));

        subjectRepository.save(new Subject(1, "Matematyka"));
        subjectRepository.save(new Subject(2, "Angielski"));
        subjectRepository.save(new Subject(3, "Polski"));
        subjectRepository.save(new Subject(4, "Historia"));
        subjectRepository.save(new Subject(5, "Niemiecki"));

        examinerRepository.save(new Examiner(1, "Slawomir", "Golibroda", "z", "z", "s.golibrodai@pb.edu.pl", true));
        examinerRepository.save(new Examiner(2, "Dorota", "Warka", "y", "y", "wedliny@pb.edu.pl", true));
        examinerRepository.save(new Examiner(3, "Julita", "Komarewska", "enjoyer", "szastprast", "smok@pb.edu.pl", false));

        List<Student> students1 = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();

        Student student1 = new Student(1, "a", "a", "a", "a", "a@student.pb.edu.pl");
        Student student2 = new Student(2, "b", "b", "b", "b", "b@student.pb.edu.pl");
        Student student3 = new Student(3, "c", "c", "c", "c", "c@student.pb.edu.pl");
        Student student4 = new Student(4, "d", "d", "d", "d", "d@student.pb.edu.pl");
        Student student5 = new Student(5, "e", "e", "e", "e", "e@student.pb.edu.pl");
        Student student6 = new Student(6, "f", "f", "f", "f", "f@student.pb.edu.pl");
        Student student7 = new Student(7, "Dziekan", "Dziekanski", "Dziekan", "123", "dziekan@student.pb.edu.pl");

        students1.add(student1);
        students1.add(student2);
        students1.add(student3);

        students2.add(student4);
        students2.add(student5);
        students2.add(student6);

        studentsRepository.save(student1);
        studentsRepository.save(student2);
        studentsRepository.save(student3);
        studentsRepository.save(student4);
        studentsRepository.save(student5);
        studentsRepository.save(student6);
        studentsRepository.save(student7);

        groupRepository.save(new Group(1, "AAA", students1));
        groupRepository.save(new Group(2, "BBB", students2));

        groupRepository.save(new Group(3, "pb01", new ArrayList<>()));
        groupRepository.save(new Group(4, "pb02", new ArrayList<>()));
        groupRepository.save(new Group(5, "pb03", new ArrayList<>()));

        examinerRepository.save(new Examiner(1, "Slawomir", "Golibroda", "z", "z", "s.golibrodai@pb.edu.pl", true));
        examinerRepository.save(new Examiner(2, "Dorota", "Cuda", "wedliny", "cuda2115", "cudawedliny@pb.edu.pl", true));
        examinerRepository.save(new Examiner(3, "Marta", "Korsarz", "pbenjoyer", "szastprast", "smoczyca@pb.edu.pl", false));

        examRepository.save(new Exam(1, "Projektowanie części w SOLIDWORKS", LocalDate.now(), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(1).get(), students1));
        examRepository.save(new Exam(2, "Retusz zdjęć rektor PB w Photoshop", LocalDate.now().minusDays(2), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(1).get(), students1));
        examRepository.save(new Exam(3, "Jak zrobić sprawozdanie w MS Paint", LocalDate.now().minusDays(2), LocalTime.now().withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withNano(0), subjectRepository.findById(3).get(), students2));

        examRepository.save(new Exam(4, "Projektowanie części w SOLIDWORKS", LocalDate.now(), LocalTime.now().withSecond(0).withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withSecond(0).withNano(0).withNano(0), subjectRepository.findById(1).get(), students1));
        examRepository.save(new Exam(5, "Całki", LocalDate.now().minusDays(2), LocalTime.now().withSecond(0).withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withSecond(0).withNano(0), subjectRepository.findById(1).get(), students1));
        examRepository.save(new Exam(6, "Jak zrobić sprawozdanie w MS Paint", LocalDate.now().minusDays(2), LocalTime.now().withSecond(0).withNano(0), LocalDate.now(), LocalTime.now().plusHours(1).withSecond(0).withNano(0), subjectRepository.findById(3).get(), students2));

//        studentsRepository.save(new Student(7, groupRepository.getReferenceById(1), "Pawel", "Kulka", "kuleczka", "123", "kule@pb.edu.pl" ));
//        studentsRepository.save(new Student(8, groupRepository.getReferenceById(2), "Michal", "Zalewski", "1", "123", "zalewski@pb.edu.pl" ));
//        studentsRepository.save(new Student(9, groupRepository.getReferenceById(3), "Tomasz", "Adamek", "a", "123", "adamczyk@pb.edu.pl" ));
 //       studentsRepository.save(new Student(10, groupRepository.getReferenceById(3), "Dziekan", "Dziekanski", "Dziekan", "123", "dziekan@pb.edu.pl" ));

        openQuestionRepository.save(new OpenQuestion(1, "Ile to 5+5?", 10, examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(2, "Ile to 30*3", 10, examRepository.getReferenceById(1)));
        openQuestionRepository.save(new OpenQuestion(3, "Ile to 19+3?", 10, examRepository.getReferenceById(1)));

        closedQuestionRepository.save(new Closedquestion(1, "ile to jest 10*10", 1, examRepository.getReferenceById(1) ));
        closedQuestionRepository.save(new Closedquestion(2, "ile to jest 11*11", 1, examRepository.getReferenceById(1) ));
        closedQuestionRepository.save(new Closedquestion(3, "ile to jest 12*12", 1, examRepository.getReferenceById(1) ));

        answerClosedRepository.save(new Answerclosed(1, "to jest moze 101?", false, closedQuestionRepository.findById(1).get()));
        answerClosedRepository.save(new Answerclosed(2, "to jest moze 102?", true, closedQuestionRepository.findById(1).get()));
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

        servicestatisticRepository.save(new Servicestatistic(0,0,studentsRepository.findAll().size(),examinerRepository.findAll().size()));
    }
}

