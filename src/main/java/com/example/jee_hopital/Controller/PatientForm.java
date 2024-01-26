package com.example.jee_hopital.Controller;

import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

@WebServlet(name="patient-form",value="/patient-form")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class PatientForm extends HttpServlet {
    private Service service;
    private boolean isLogged;

    @Override
    public void init() throws ServletException {
        service = new Service();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        isLogged = false;

        Cookie[] cookies = req.getCookies();

        for (Cookie c : cookies) {
            if (c.getName().equals("isLogged")) {
                isLogged = true;
                break;
            }
        }

        if (!isLogged) {
            req.setAttribute("mode","signin");
            req.getRequestDispatcher("sign").forward(req,resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/patient-form.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        Date birthdate = Date.valueOf(req.getParameter("birthdate"));

        Part imagePart = req.getPart("picture");
        byte[] imageBytes = null;
        if (imagePart != null) {
            InputStream inputStream = imagePart.getInputStream();
            imageBytes = inputStream.readAllBytes();
        }

        Patient patient = new Patient(firstname, lastname, birthdate, imageBytes);
        patient = service.createPatient(patient);

        if (patient != null) {
            req.setAttribute("message", "Patient correctement créé !");
            req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Quelque chose s'est mal passé...");
            req.getRequestDispatcher("/WEB-INF/patient-form.jsp").forward(req,resp);
        }
    }
}
