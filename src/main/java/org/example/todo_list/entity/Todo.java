package org.example.todo_list.entity;

public class Todo {
    private String title;
    private String description;
    private boolean ended;


    public Todo(String title, String description, boolean ended) {
        this.title = title;
        this.description = description;
        this.ended = ended;
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

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
