package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personne")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    private String email;
}
