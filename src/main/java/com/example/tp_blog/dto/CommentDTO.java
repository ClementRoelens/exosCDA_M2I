package com.example.tp_blog.dto;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CommentDTO {
    private UUID id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String content;
    private PostDTO attachedPost;


    public CommentDTO() {
        id = UUID.randomUUID();
    }

    public CommentDTO(String name, String email, String content) {
        this();
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public CommentDTO(String name, String email, String content, PostDTO attachedPost) {
        this();
        this.name = name;
        this.email = email;
        this.content = content;
        this.attachedPost = attachedPost;
    }

    public CommentDTO(UUID id, String name, String email, String content) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.content = content;
    }

    public CommentDTO(UUID id, String name, String email, String content, PostDTO attachedPost) {
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

    public PostDTO getAttachedPost() {
        return attachedPost;
    }

    public void setAttachedPost(PostDTO attachedPost) {
        this.attachedPost = attachedPost;
    }

    public Comment toComment(){
        return new Comment(id,name,email,content);
    }
}
