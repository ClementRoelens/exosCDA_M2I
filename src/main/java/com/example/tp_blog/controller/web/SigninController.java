package com.example.tp_blog.controller.web;

import com.example.tp_blog.service.SigninService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController {

    private final SigninService signinService;

    public SigninController(SigninService signinService){
        this.signinService = signinService;
    }

    @GetMapping("/signin")
    public String signin(Model model){
        return "signin";
    }

    @PostMapping("/signinAction")
    public String signin(@RequestParam String name, @RequestParam String password, @RequestParam String desiredPage){
        if (signinService.login(name,password)){
            return "redirect:/" + desiredPage;
        }
        return "error/wrong-credentials";
    }
}
