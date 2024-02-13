package org.example.demo_base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/home")
public class HelloRestController {

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String sayHello(){
        System.out.println("Hello console");
        return "Hello client";
    }

    @GetMapping(value = "persons")
    public List<String> sayHelloJson(){
        return List.of("John Cena", "Jeff Hardy", "Eric Zemmour");
    }
}
