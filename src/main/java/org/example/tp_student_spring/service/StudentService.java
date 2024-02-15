package org.example.tp_student_spring.service;

import org.example.tp_student_spring.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentByid(int id);
    List<Student> seekStudentsByName(String name);
    boolean createStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}
