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
        model.addAttribute("students",studentService.getAllStudents());
        return "pages/show-students";
    }

    @GetMapping("/addStudentForm")
    public String addStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "pages/add-student";
    }

    @PostMapping("/addStudentAction")
    public String addStudent(@ModelAttribute("student") Student student, Model model){
        if (studentService.createStudent(student)){
            return "pages/success";
        }
        model.addAttribute("message", "Un étudiant doit avoir 18 ans");
        return "pages/fail";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id")int id, Model model){
        Student student = studentService.getStudentByid(id);
        if (student == null){
            model.addAttribute("message","Aucun étudiant à cet id...");
            return "pages/fail";
        }
        model.addAttribute("student",student);
        return "pages/student-details";
    }

    @GetMapping("/seek")
    public String seek(@RequestParam("name") String name, Model model){
        List<Student> students = studentService.seekStudentsByName(name);
        if (students.isEmpty()){
            model.addAttribute("message", "Aucun étudiant trouvé");
            return "pages/fail";
        }
        model.addAttribute("students", students);
        return "pages/show-students";
    }
}
