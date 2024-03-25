package org.example.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.backend.entity.Todo;
import org.example.backend.entity.User;

import java.util.UUID;

public class TodoDTO {
    private UUID id;
    private String title;
    private String description;
    private boolean isCompleted;

    public TodoDTO(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public TodoDTO(UUID id, String title, String description, boolean isCompleted) {
        this(title,description,isCompleted);
        this.id = id;
    }
    
    public Todo toTodo(){
        return new Todo(id,title,description,isCompleted);
    }
}
