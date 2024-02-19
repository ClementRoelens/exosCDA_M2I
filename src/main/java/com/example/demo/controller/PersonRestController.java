package com.example.demo.controller;


import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v0/person")
public class PersonRestController {

    private PersonService personService;

    @GetMapping("/")
    public List<Person> getAllPersons(){
        return personService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<String> createPerson(@RequestBody Person person){
        personService.save(person);
        return new ResponseEntity<>("Personne bien créée", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable int id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/")
    public Person updatePerson(@RequestBody Person person){
        return personService.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        personService.delete(id);
        return new ResponseEntity<>("Personne supprimée",HttpStatus.ACCEPTED);
    }


}
