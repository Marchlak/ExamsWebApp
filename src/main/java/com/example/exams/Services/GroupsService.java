package com.example.exams.Services;

import com.example.exams.Model.Data.db.Group;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Repositories.Db.GroupEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {
    @Autowired
    GroupEntityRepository groupsRepository;

    @Autowired
    StudentsService studentsService;

    public List<Group> getAllGroups() {
        return groupsRepository.findAll();
    }

    public Group getGroupByGroupId(Integer groupId) {
        return groupsRepository.findGroupById(groupId);
    }

    public void removeStudent(Integer groupId, Integer studentId) {
        Group group = groupsRepository.findGroupById(groupId);
        Student student = studentsService.getStudentById(studentId);

        if(group != null && student != null) {
            if(group.getStudents().contains(student)){
                group.getStudents().remove(student);
                groupsRepository.save(group);
            }
        }
    }
    public void addStudent(Integer groupId, Integer studentId) {
        Group group = groupsRepository.findGroupById(groupId);
        Student student = studentsService.getStudentById(studentId);

        if(group != null && student != null) {
            if(!group.getStudents().contains(student)){
                group.getStudents().add(student);
                groupsRepository.save(group);
            }
        }
    }
}
