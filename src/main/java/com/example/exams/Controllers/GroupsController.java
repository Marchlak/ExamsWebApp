package com.example.exams.Controllers;

import com.example.exams.Model.Data.db.Group;
import com.example.exams.Model.Data.db.Student;
import com.example.exams.Services.GroupsService;
import com.example.exams.Services.StudentsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;


@Controller
public class GroupsController {
    final StudentsService studentsService;
    final GroupsService groupsService;

    public GroupsController(StudentsService studentsService, GroupsService groupsService){
        this.studentsService = studentsService;
        this.groupsService = groupsService;
    }

    @GetMapping("/groups")
    public ModelAndView groups(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("groupsView");
        return modelAndView;
    }

    @GetMapping("/addGroup")
    public ModelAndView addGroups(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addGroup");
        return modelAndView;
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
        return "redirect:/manageGroup/" + groupId;
    }
    @PostMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("groupId") String groupId, @RequestParam("studentId") String studentId){
        try {
            int parsedGroupId = Integer.parseInt(groupId);
            int parsedStudentId = Integer.parseInt(studentId);
            this.groupsService.removeStudent(parsedGroupId, parsedStudentId);
        }catch (NumberFormatException e) {
            System.err.println("Parsing error - groupId: " + groupId);
        }
        return "redirect:/manageGroup/" + groupId;
    }

    @GetMapping("/editStudent/{groupId}/{studentId}")
    public ModelAndView showEditStudentForm(@PathVariable Integer groupId, @PathVariable Integer studentId){
        ModelAndView modelAndView = new ModelAndView();

        Student student = this.studentsService.getStudentById(studentId);

        modelAndView.addObject("student", student);
        modelAndView.addObject("groupId", groupId);

        modelAndView.setViewName("editStudent");
        return modelAndView;
    }
    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute("editedStudent") Student editedStudent, @ModelAttribute("groupId") String groupId){
        Integer studentId = editedStudent.getStudentId();
        String firstName = editedStudent.getFirstname();
        String lastName = editedStudent.getLastname();
        String login = editedStudent.getLogin();
        String password = editedStudent.getPassword();
        String email = editedStudent.getEmail();

        studentsService.editStudent(studentId, firstName, lastName, login, password, email);
        return "redirect:/manageGroup" + groupId;
    }
}
