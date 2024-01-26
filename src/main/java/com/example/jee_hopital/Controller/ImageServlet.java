package com.example.jee_hopital.Controller;

import com.example.jee_hopital.Entities.Patient;
import com.example.jee_hopital.Service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "image", value = "/image")
public class ImageServlet extends HttpServlet {

    private Service service;

    @Override
    public void init() throws ServletException {
        service = new Service();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Patient patient = service.getPatient(id);
        if (patient != null) {
            OutputStream out = resp.getOutputStream();
            out.write(patient.getPicture());
            out.close();
        }
    }
}
