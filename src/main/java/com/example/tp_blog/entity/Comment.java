package com.example.tp_blog.entity;

public class Comment {
    private int id;
    private String name;
    private String email;
    private String content;
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
