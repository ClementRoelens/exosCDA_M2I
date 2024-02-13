package org.example.demo_base.controller;

import org.example.demo_base.service.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloControllerSet {
    // Injection de dépendance

    private final GreetingsService _greetingsService;

//    @Autowired

    public HelloControllerSet(@Qualifier("salutations") GreetingsService greetingsService){
        _greetingsService = greetingsService;
    }

    @RequestMapping(value = "/hello-set")
    public String sayHello(){
        System.out.println(_greetingsService.sayHello());
        return "home";
    }
}
