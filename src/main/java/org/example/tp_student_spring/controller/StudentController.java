package org.example.tp_student_spring.controller;

import org.example.tp_student_spring.entity.Student;
import org.example.tp_student_spring.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(){
        return "pages/index";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "pages/show-students";
    }

    @GetMapping("/addStudentForm")
    public String addStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "pages/add-student";
    }

    @PostMapping("/addStudentAction")
    public String addStudent(@ModelAttribute("student") Student student){
        if (studentService.createStudent(student)){
            return "pages/success";
        }
        return "pages/fail";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id")int id, Model model){
        Student student = studentService.getStudentByid(id);
        model.addAttribute("student",student);
        return "pages/student-details";
    }

    @GetMapping("/seek")
    public String seek(@RequestParam("name") String name, Model model){
        List<Student> students = studentService.seekStudentsByName(name);
        model.addAttribute("students", students);
        return "pages/show-students";
    }
}
