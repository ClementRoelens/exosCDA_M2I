package org.example.todo_list.controller;

import org.example.todo_list.entity.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TodoController {

    @RequestMapping(value = "/todos")
    public String getTodos(Model model){
        List<Todo> todos = List.of(
                new Todo("Finir l'exercice", "Il faut juste se souvenir de la consigne...", true),
                new Todo("Finir le module", "Franchement ça devrait passer !", false),
                new Todo("Conquérir le monde", "À DOS D'UN LAPIN GÉANT MWAHAHAHA", false)
        );
        model.addAttribute("todos", todos);
        return "todos";
    }
}
