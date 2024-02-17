package com.example.tp_blog.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Comment {
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String content;
    @NotNull
    private Post attachedPost;


    public Comment() {
    }

    public Comment(String name, String email, String content) {
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public Comment(String name, String email, String content, Post attachedPost) {
        this.name = name;
        this.email = email;
        this.content = content;
        this.attachedPost = attachedPost;
    }

    public Comment(int id, String name, String email, String content, Post attachedPost) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
        this.attachedPost = attachedPost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getAttachedPost() {
        return attachedPost;
    }

    public void setAttachedPost(Post attachedPost) {
        this.attachedPost = attachedPost;
    }
}
