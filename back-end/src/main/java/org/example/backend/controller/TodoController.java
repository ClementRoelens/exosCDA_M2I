package org.example.backend.controller;

import org.example.backend.dto.TodoDTO;
import org.example.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoDTO> getAllTodos(){
        return todoService.getAllTodos().stream().map()
    }

    public 
}
