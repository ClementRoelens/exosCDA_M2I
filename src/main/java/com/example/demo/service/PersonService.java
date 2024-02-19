package com.example.demo.service;

import com.example.demo.entity.Person;

import java.util.List;

public interface PersonService {
    void save(Person person);
    List<Person> findAll();
    Person findById(int id);
    Person update(Person person);
    void delete(int id);

}
