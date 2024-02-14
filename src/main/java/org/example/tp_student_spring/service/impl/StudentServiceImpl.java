package org.example.tp_student_spring.service.impl;

import org.example.tp_student_spring.entity.Student;
import org.example.tp_student_spring.service.StudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> students;

    public StudentServiceImpl() {
        students = new ArrayList<>();
        students.add(new Student("Clément", "Roelens", LocalDate.of(1991, 9, 2), "clement.roelens@hotmail.com"));
        students.add(new Student("Nassim", "Sakhri", LocalDate.of(1996, 3, 17), "nassim.sakhri@gmail.com"));
        students.add(new Student("Pauline", "Laout", LocalDate.of(1992, 12, 20), "p.laout@gmail.com"));
        students.add(new Student("Olivia", "Pigani", LocalDate.of(1995, 10, 1), "oliv.p@gmail.com"));
        students.add(new Student("Clémence", "Petit", LocalDate.of(1994, 1, 5), "jesaispaslol@ixdé.com"));
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
        // Vérification bien dégueulasse si l'étudiant a bien 18 ans...
        if (LocalDate.now().getYear() - student.getBirthdate().getYear() < 18) {
            return false;
        }
        if ((LocalDate.now().getYear() - student.getBirthdate().getYear() == 18)
                &&
                (LocalDate.now().getMonthValue() - student.getBirthdate().getMonthValue() < 0)) {
            return false;
        }
        if ((LocalDate.now().getYear() - student.getBirthdate().getYear() == 18)
                &&
                (LocalDate.now().getMonthValue() - student.getBirthdate().getMonthValue() == 0)
                &&
                (LocalDate.now().getDayOfMonth() - student.getBirthdate().getDayOfMonth() < 0)
        ) {
            return false;
        }

        students.add(student);
        return true;
    }
}
