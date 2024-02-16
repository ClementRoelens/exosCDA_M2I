package com.example.tp_blog.entity;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private int id;
    private String title;
    private String description;
    private String content;
    private List<Comment> comments;


    public Post() {
        this.comments = new ArrayList<>();
    }

    public Post(String title, String description, String content) {
        this();
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Post(int id, String title, String description, String content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment){
        comments.add(comment);
    }
}
