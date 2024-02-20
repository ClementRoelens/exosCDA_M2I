package com.example.tp_blog.dto;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostDTO {
    private UUID id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String content;
    private List<CommentDTO> comments;


    public PostDTO() {
        this.comments = new ArrayList<>();
        id = UUID.randomUUID();
    }

    public PostDTO(String title, String description, String content) {
        this();
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public PostDTO(UUID id, String title, String description, String content, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.comments = comments;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }


    public void addComment(CommentDTO comment){
        comments.add(comment);
    }

    public Post toPost(){
        return new Post(id,title,description,content,comments.stream().map(CommentDTO::toComment).collect(Collectors.toList()));
    }
}
