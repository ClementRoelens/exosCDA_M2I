package com.example.tp_blog.entity;

import com.example.tp_blog.dto.PostDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "post")
public class Post {
    @Id
    private UUID id;
    private String title;
    private String description;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "attachedPost")
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

    public Post(UUID id, String title, String description, String content, List<Comment> comments) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public void addComment(Comment comment){
        comments.add(comment);
    }

    public PostDTO toDTO(){
        return new PostDTO(id,title,description,content,comments.stream().map(Comment::toDTO).collect(Collectors.toList()));
    }
}
