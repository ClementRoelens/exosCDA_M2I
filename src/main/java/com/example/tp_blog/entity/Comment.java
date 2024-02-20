package com.example.tp_blog.entity;

import com.example.tp_blog.dto.CommentDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Comment {
    @Id
    private UUID id;
    private String name;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "post_id")
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

    public Comment(UUID id, String name, String email, String content) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public Comment(UUID id, String name, String email, String content, Post attachedPost) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
        this.attachedPost = attachedPost;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public CommentDTO toDTO(){
        return new CommentDTO(id,name,email,content);
    }
}
