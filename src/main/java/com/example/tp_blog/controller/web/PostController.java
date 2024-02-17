package com.example.tp_blog.controller.web;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final BlogServiceImpl blogService;

    public PostController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts", blogService.getPosts());
        return "home";
    }

    @GetMapping("/createPost")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "post-form";
    }

    @PostMapping("/createPostAction")
    public String createPostAction(@Valid @ModelAttribute("post") Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "post-form";
        }
        blogService.createPost(post);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String getPostBydId(@PathVariable int id, Model model){
        model.addAttribute("post", blogService.getPostById(id));
        model.addAttribute("comment", new Comment());
        return "post";
    }
}
