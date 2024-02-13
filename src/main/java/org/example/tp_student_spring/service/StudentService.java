package org.example.tp_student_spring.service;

import org.example.tp_student_spring.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentByid(int id);
    List<Student> seekStudentsByName(String name);
    boolean createStudent(String firstName, String lastName, int age, String email);
}
