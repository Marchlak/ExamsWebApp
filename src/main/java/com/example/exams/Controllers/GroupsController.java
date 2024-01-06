package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Group;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Services.GroupsService;
import com.example.exams.Services.StudentsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GroupsController {
    final StudentsService studentsService;
    final GroupsService groupsService;

    public GroupsController(StudentsService studentsService, GroupsService groupsService){
        this.studentsService = studentsService;
        this.groupsService = groupsService;
    }

    @GetMapping("/manageGroup/{groupId}")
    public ModelAndView manageGroup(@PathVariable Integer groupId){
        ModelAndView modelAndView = new ModelAndView();

        Group group = this.groupsService.getGroupByGroupId(groupId);

        List<Student> addedStudents = group.getStudents();
        List<Student> allStudents = studentsService.getAllStudents();

        List<Student> availableStudents = allStudents.stream()
                .filter(student -> !addedStudents.contains(student))
                .collect(Collectors.toList());

        modelAndView.addObject("students", availableStudents);
        modelAndView.addObject("addedStudents", addedStudents);

        modelAndView.setViewName("manageGroup");
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam("groupId") String groupId, @RequestParam("studentId") Integer studentId) {
        try{
            int parsedGroupId = Integer.parseInt(groupId);
            this.groupsService.addStudent(parsedGroupId, studentId);
        }catch(NumberFormatException e){
            System.err.println("Parsing error - groupId: " + groupId);
        }
        return "redirect:/groups/" + groupId;
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("groupId") String groupId, @RequestParam("studentId") Integer studentId){
        try {
            int parsedGroupId = Integer.parseInt(groupId);
            this.groupsService.removeStudent(parsedGroupId, studentId);
        }catch (NumberFormatException e) {
            System.err.println("Parsing error - groupId: " + groupId);
        }
        return "redirect:/groups/" + groupId;
    }
}
