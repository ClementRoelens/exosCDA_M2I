package org.example.tp_student_spring.controller;

import jakarta.websocket.server.PathParam;
import jakarta.xml.ws.Response;
import org.example.tp_student_spring.entity.Student;
import org.example.tp_student_spring.service.impl.StudentServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentRestController {

    private final StudentServiceImpl studentService;

    public StudentRestController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public void createStudent(@RequestBody Student student){
        studentService.createStudent(student);
    }

    @GetMapping("/")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentByid(id);
    }

    @GetMapping("/seek/{seekedName}")
    public List<Student> seekStudent(@PathVariable String seekedName){
        return studentService.seekStudentsByName(seekedName);
    }

    @PutMapping("/")
    public Student updateStudent(@RequestBody Student student){
        if (studentService.updateStudent(student)){
            return studentService.getStudentByid(student.getId());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }
}
