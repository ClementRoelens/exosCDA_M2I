package org.example.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.backend.entity.Todo;
import org.example.backend.entity.User;


public class TodoDTO {
    private String id;
    private String title;
    private String description;
    private boolean isCompleted;
    private String userEmail;


    public TodoDTO() {
    }

    public TodoDTO(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public TodoDTO(String id, String title, String description, boolean isCompleted) {
        this(title,description,isCompleted);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Todo toTodo(){
        return new Todo(id,title,description,isCompleted);
    }
}
