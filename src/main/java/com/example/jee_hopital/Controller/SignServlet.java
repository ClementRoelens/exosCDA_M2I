package com.example.jee_hopital.Controller;

import com.example.jee_hopital.Entities.User;
import com.example.jee_hopital.Service.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "sign", value = "/sign")
public class SignServlet extends HttpServlet {
    private Service service;

    @Override
    public void init() throws ServletException {
        service = new Service();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = (req.getParameter("mode").equals("signin")) ?
                "Connexion" : "Inscription";
        req.setAttribute("title", title);
        req.getRequestDispatcher("/views/sign-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("passwordConfirmation");

        // Si passwordConfirmation n'est pas nul, on est dans une inscription

        if (passwordConfirmation != null) {
            if (!password.equals(passwordConfirmation)) {
                req.setAttribute("error", true);
                req.setAttribute("message", "Entrez deux fois le même mot de passe");
                req.setAttribute("title", "Inscription");
                req.getRequestDispatcher("/views/sign-form.jsp").forward(req, resp);
            } else {
                User user = new User(name, password);
                user = service.createUser(user);

                if (user != null) {
                    req.setAttribute("message", "Utilisateur correctement créé");
                    req.getRequestDispatcher("/views/success.jsp").forward(req, resp);
                }
            }
        } else {
            // Sinon, on est dans une connexion

            User user = new User(name, password);

            if (!service.signin(user)) {
                req.setAttribute("error", true);
                req.setAttribute("message", "Informations d'authentification incorrects");
                req.getRequestDispatcher("/views/sign-form.jsp").forward(req, resp);
            }

            Cookie cookie = new Cookie("isLogged", "Clément");
            cookie.setMaxAge(3600 * 24);
            resp.addCookie(cookie);
            resp.sendRedirect("home");
        }


    }
}
