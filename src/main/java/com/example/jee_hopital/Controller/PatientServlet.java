package com.example.jee_hopital.Controller;

import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

@WebServlet(name = "patient", value = "/patient")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class PatientServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() throws ServletException {
        service = new Service();
    }
}

