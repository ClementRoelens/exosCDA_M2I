package org.example.backend.controller;

import org.example.backend.dto.TodoDTO;
import org.example.backend.entity.Todo;
import org.example.backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public TodoDTO createTodo(@RequestBody TodoDTO todoDTO){
        return todoService.createOrUpdateTodo(todoDTO).toDTO();
    }

    @GetMapping
    public List<TodoDTO> getAllTodos(){
        return todoService.getAllTodos().stream().map(Todo::toDTO).toList();
    }

    @GetMapping("/{id}")
    public TodoDTO getOneTodo(@PathVariable String id){
        return todoService.getOneTodo(id).toDTO();
    }

    @GetMapping("/getTheirTodos/{userId}")
    public List<TodoDTO> getTheirTodos(@PathVariable String userId){
        return todoService.getTheirTodos(userId).stream().map(Todo::toDTO).toList();
    }

    @PutMapping
    public TodoDTO updateTodo(@RequestBody TodoDTO todoDTO){
        return todoService.createOrUpdateTodo(todoDTO).toDTO();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteDTO(String id){
        Todo todo = todoService.getOneTodo(id);

        boolean res = todoService.deleteTodo(todo);
        if (res){
            return new ResponseEntity<>("Tâche bien supprimée",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("La tâche n'a pas pu être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
