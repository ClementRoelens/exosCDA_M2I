package org.example.tp_student_spring.service.impl;

import org.example.tp_student_spring.entity.Student;
import org.example.tp_student_spring.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> students;

    public StudentServiceImpl() {
        students = List.of(
                new Student("Clément","Roelens",32,"clement.roelens@hotmail.com"),
                new Student("Nassim","Sakhri",27,"nassim.sakhri@gmail.com"),
                new Student("Pauline","Laout",31,"p.laout@gmail.com"),
                new Student("Olivia","Pigani",28,"oliv.p@gmail.com"),
                new Student("Clémence","Petit",30,"jesaispaslol@ixdé.com")
        );
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
                .filter(s -> s.getFirstName().contains(name) || s.getLastName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean createStudent(String firstName, String lastName, int age, String email) {
        if (age < 18){
            System.out.println("Un étudiant doit avoir 18 ans");
            return false;
        }
        Student student = new Student(firstName,lastName,age,email);
        students.add(student);
        return true;
    }
}
