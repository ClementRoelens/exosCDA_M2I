package org.example.tp_student_spring.service.impl;

import org.example.tp_student_spring.entity.Student;
import org.example.tp_student_spring.service.StudentService;
import org.example.tp_student_spring.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> students;
    private int count;

    public StudentServiceImpl() {
        students = new ArrayList<>();
        students.add(new Student(count++, "Clément", "Roelens", LocalDate.of(1991, 9, 2), "clement.roelens@hotmail.com"));
        students.add(new Student(count++, "Nassim", "Sakhri", LocalDate.of(1996, 3, 17), "nassim.sakhri@gmail.com"));
        students.add(new Student(count++, "Pauline", "Laout", LocalDate.of(1992, 12, 20), "p.laout@gmail.com"));
        students.add(new Student(count++, "Olivia", "Pigani", LocalDate.of(1995, 10, 1), "oliv.p@gmail.com"));
        students.add(new Student(count++, "Clémence", "Petit", LocalDate.of(1994, 1, 5), "jesaispaslol@ixdé.com"));
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudentByid(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Student> seekStudentsByName(String name) {
        return students.stream()
                .filter(s -> s.getFirstName().toLowerCase().contains(name.toLowerCase()) || s.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean createStudent(Student student) {
        if (Util.getAgeFromDate(student.getBirthdate()) >= 18) {
            student.setId(count++);
            students.add(student);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = getStudentByid(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }
}
