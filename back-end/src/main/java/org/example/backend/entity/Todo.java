package org.example.backend.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "todo_id")
    private UUID id;
    @Column(name = "todo_title")
    private String title;
    @Column(name = "todo_description")
    private String description;
    @Column(name = "todo_isCompleted")
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Todo() {
    }

    public Todo(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        isCompleted = true;
    }

    public Todo(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
