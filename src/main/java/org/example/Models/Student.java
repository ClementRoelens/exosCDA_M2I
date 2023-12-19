package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int classNumber;
    private Date gradeDate;
}
