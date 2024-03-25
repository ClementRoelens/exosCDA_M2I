package org.example.backend.service;

import org.example.backend.dto.TodoDTO;
import org.example.backend.entity.Todo;
import org.example.backend.repository.TodoRepository;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public List<Todo> getTheirTodos(UUID id){
        return todoRepository.findTodosByUser(userRepository.findById(id).orElse(null));
    }

    public Todo getOneTodo(UUID id){
        return todoRepository.findById(id).orElse(null);
    }

    public Todo createOrUpdateTodo(TodoDTO todoDTO){
        Todo todo = todoDTO.toTodo();
        return todoRepository.save(todo);
    }

    public boolean deleteTodo(Todo todo){
        todoRepository.delete(todo);
        return true;
    }
}
