package org.example.tp_student_spring.entity;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


public class Student {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
    @NotNull
    private LocalDate birthdate;
    @NotNull
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Un e-mail doit être de type 'user@domain.com'")
    private String email;


    public Student() {
    }

    public Student(String firstName, String lastName, LocalDate birthdate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }


    public Student(int id, String firstName, String lastName, LocalDate birthdate, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setbirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentForm toStudentForm(){
        return new StudentForm(id,firstName,lastName,birthdate.toString(),email);
    }
}
