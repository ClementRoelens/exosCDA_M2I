package com.example.tp_blog.controller.rest;

import com.example.tp_blog.dto.CommentDTO;
import com.example.tp_blog.dto.PostDTO;
import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentRestController {

    private final BlogServiceImpl blogService;

    public CommentRestController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Object> createComment(@RequestBody @Valid CommentDTO comment, BindingResult bindingResult, @PathVariable UUID postId){
        PostDTO post = blogService.getPostById(postId);

        if (post == null){
            return new ResponseEntity<>("Aucun post n'a été trouvé à cet id",HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();

            for (ObjectError o : bindingResult.getAllErrors()){
                errors.append(o.toString());
                errors.append("\n");
            }

            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        comment = blogService.createComment(comment, post);
        if (comment != null){
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public List<CommentDTO> getComments(){
        return blogService.getComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getComment(@PathVariable UUID id){
        CommentDTO comment = blogService.getCommentById(id);

        if (comment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comment, HttpStatus.ACCEPTED);
    }
}
