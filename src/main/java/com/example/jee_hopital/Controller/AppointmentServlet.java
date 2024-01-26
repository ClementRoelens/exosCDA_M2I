package com.example.jee_hopital.Controller;

import com.example.jee_hopital.Entities.Appointment;
import com.example.jee_hopital.Entities.CareSheet;
import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name="appointment", value="/appointment")
public class AppointmentServlet extends HttpServlet {

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
            req.getRequestDispatcher("/WEB-INF/appointment-form.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");

        if (mode == null){
            String name = req.getParameter("name");
            Date date = Date.valueOf(req.getParameter("date"));
            int patientId = Integer.parseInt(req.getParameter("patientId"));

            Patient patient = service.getPatient(patientId);

            Appointment appointment = new Appointment(name,date, patient);
            appointment = service.createAppointment(appointment);

            if (appointment != null){
                req.setAttribute("appointment", appointment);
                req.getRequestDispatcher("/views/appointment-detail.jsp").forward(req,resp);
            }
        } else {
            int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
            int duration = Integer.parseInt(req.getParameter("duration"));
            Appointment appointment = service.getAppointment(appointmentId);

            if (mode.equals("addCareSheet")){
                String care = req.getParameter("care");
                CareSheet careSheet = new CareSheet(care,duration,appointment);
                appointment.getCareSheets().add(careSheet);
                    req.setAttribute("appointment", appointment);
                if (service.updateAppointment(appointment)){
                    req.setAttribute("error", true);
                    req.setAttribute("message","La feuille de soins n'a pas pu être créée");
                    req.getRequestDispatcher("/views/appointment-detail.jsp").forward(req,resp);
                }
            }
        }
    }
}
