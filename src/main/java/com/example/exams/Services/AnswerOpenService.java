package com.example.exams.Services;

import com.example.exams.Model.Data.db.Student;
import com.example.exams.Model.Data.db.Studentopenanswer;
import com.example.exams.Repositories.Db.AnswerClosedRepository;
import com.example.exams.Repositories.Db.StudentclosedanswerRepository;
import com.example.exams.Repositories.Db.StudentopenanswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AnswerOpenService {

    private final StudentopenanswerRepository studentopenanswerRepository;
    @Autowired
    public AnswerOpenService(StudentopenanswerRepository studentopenanswerRepository){
        this.studentopenanswerRepository = studentopenanswerRepository;
    }
    public List<Student> getAllDistinctStudentsForOpenQuestions(int examId) {
        List<Studentopenanswer> studentopenanswers = studentopenanswerRepository.findAllByOpenquestionQuestionid_Exam_Id(examId);

        Set<Student> uniqueStudents = studentopenanswers.stream()
                .map(Studentopenanswer::getStudentStudent)
                .collect(Collectors.toSet());

        return List.copyOf(uniqueStudents);
    }
    public List<Studentopenanswer> getStudentOpenAnswerByStudent(Student student){
        return studentopenanswerRepository.findAllByStudentStudent(student);
    }

    public void updateScores(Student student, List<Integer> scores) {
        List<Studentopenanswer> studentOpenAnswers = getStudentOpenAnswerByStudent(student);
        if (studentOpenAnswers.size() != scores.size()) {
            throw new IllegalArgumentException("List sizes do not match");
        }

        for (int i = 0; i < studentOpenAnswers.size(); i++) {
            Studentopenanswer answer = studentOpenAnswers.get(i);
            Integer score = scores.get(i);
            answer.setScore(score);
            studentopenanswerRepository.save(answer);
        }
    }
}
