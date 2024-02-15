package org.example.tp_student_spring.entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class StudentForm {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
    @NotNull
    @Size(min = 10, max = 10)
    private String birthdate;
    @NotNull
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Un e-mail doit être de type 'user@domain.com'")
    private String email;

    public StudentForm() {
    }

    public StudentForm(int id, String firstName, String lastName, String birthdate, String email) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student toStudent(){
        return new Student(id,firstName,lastName, LocalDate.parse(birthdate),email);
    }
}
