package org.example.todo_list.controller;

import org.example.todo_list.entity.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/api/v0")
public class TodoRestController {

    @GetMapping(value = "todos")
    public List<Todo> getTodos(){
        List<Todo> todos = List.of(
                new Todo("Finir l'exercice", "Il faut juste se souvenir de la consigne...", true),
                new Todo("Finir le module", "Franchement ça devrait passer !", false),
                new Todo("Conquérir le monde", "À DOS D'UN LAPIN GÉANT MWAHAHAHA", false)
        );
        return todos;
    }

    @GetMapping(value = "todo")
    public Todo getTodo(){
        return new Todo("Faire un gâteau", "Une forêt noire hmmm... Ou ptet un gâteau au yaourt ? Mais avec un nappage au chocolat", false);
    }
}
