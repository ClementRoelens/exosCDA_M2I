package com.example.tp_blog.controller.rest;

import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        post = blogService.createPost(post);
        if (post != null){
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
