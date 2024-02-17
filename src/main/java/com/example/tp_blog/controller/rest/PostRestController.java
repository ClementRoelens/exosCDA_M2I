package com.example.tp_blog.controller.rest;

import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostRestController {

    private final BlogServiceImpl blogService;

    public PostRestController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createPost(@RequestBody @Valid Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();

            for (ObjectError o : bindingResult.getAllErrors()){
                errors.append(o.toString());
                errors.append("\n");
            }

            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
        post = blogService.createPost(post);
        if (post != null){
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/")
    public List<Post> getPosts(){
        return blogService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id){
        return blogService.getPostById(id);
    }


}
