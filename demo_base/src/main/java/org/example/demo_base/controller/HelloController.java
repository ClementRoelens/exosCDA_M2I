package org.example.demo_base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {
    @RequestMapping(value = "/")
    public String sayHello(){
        System.out.println("coucou console");
        return "hello";
    }

    @RequestMapping(value = "/persons")
    @ResponseBody
    public List<String> getPersons(){
        return List.of("John Cena", "Jeff Hardy", "Eric Zemmour");
    }

    @RequestMapping(value = "/toto")
    public String toto(){
        return "toto";
    }


    @RequestMapping("/home/person")
    public String personName(Model model){
//        model.addAttribute("firstName","Adolf");
//        model.addAttribute("lastName","Hitler");

        List<String> persons = List.of("Benito Mussolini", "Adolf Hitler", "Emmanuel Macron");

        model.addAttribute("persons", persons);

        return "person/details";
    }
}
