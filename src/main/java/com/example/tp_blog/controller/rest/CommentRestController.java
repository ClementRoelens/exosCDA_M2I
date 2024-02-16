package com.example.tp_blog.controller.rest;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentRestController {

    private final BlogServiceImpl blogService;

    public CommentRestController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        comment = blogService.createComment(comment);
        if (comment != null){
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
