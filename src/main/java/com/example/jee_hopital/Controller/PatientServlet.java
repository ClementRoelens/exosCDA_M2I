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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "patient", value = "/patient")
public class PatientServlet extends HttpServlet {
    private Service service;
    List<Patient> patients;
    Patient patient;


    @Override
    public void init() throws ServletException {
        service = new Service();
        patients = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (name == null){
            String id = req.getParameter("id");
            if (id != null){
                patient = service.getPatient(Integer.parseInt(id));
                req.setAttribute("patient",patient);
                req.getRequestDispatcher("/views/patient-detail.jsp").forward(req,resp);
            } else {
                patients = service.getPatients();
                req.setAttribute("patients", patients);
                req.getRequestDispatcher("/views/patient-list.jsp").forward(req,resp);
            }
        } else {
            patients = service.seekPatientsByName(name);
            req.setAttribute("patients",patients);
            req.getRequestDispatcher("/views/patient-list.jsp").forward(req,resp);
        }
    }
}

