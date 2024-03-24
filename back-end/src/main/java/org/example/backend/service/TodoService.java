package org.example.backend.service;

import org.example.backend.dto.TodoDTO;
import org.example.backend.entity.Todo;
import org.example.backend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo getOneTodo(UUID id){
        return todoRepository.findById(id).orElse(null);
    }

    public Todo createTodo(TodoDTO todoDTO){
        Todo todo = todoDTO.toTodo();
        return todoRepository.save();
    }

    public boolean updateTodo(TodoDTO todoDTO)
}
