package com.example.tp_blog.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SigninService {

    @Autowired
    private HttpSession httpSession;

    public boolean isAuthorized() {
        try {
            return httpSession.getAttribute("login").equals("ok");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean login(String name, String password){
        if (name.equals("admin") && password.equals("admin")){
            httpSession.setAttribute("login","ok");
            return true;
        }
        return false;
    }
}
