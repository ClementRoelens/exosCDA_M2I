package com.example.demo.service;

import com.example.demo.dao.PersonRepository;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {

        return personRepository.findAll();
    }

    @Override
    public Person findById(int id) {
        System.out.println("test : " + id);
        return personRepository.getReferenceById(id);
    }

    @Override
    public Person update(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }

}
