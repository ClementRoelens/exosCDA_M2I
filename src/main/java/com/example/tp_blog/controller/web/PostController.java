package com.example.tp_blog.controller.web;

import com.example.tp_blog.dto.CommentDTO;
import com.example.tp_blog.dto.PostDTO;
import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.SigninService;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class PostController {

    private final BlogServiceImpl blogService;
    private final SigninService signinService;

    public PostController(BlogServiceImpl blogService, SigninService signinService) {
        this.blogService = blogService;
        this.signinService = signinService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("posts", blogService.getPosts());
        return "home";
    }

    @GetMapping("/createPost")
    public String createPost(Model model){
        if (signinService.isAuthorized()){
            model.addAttribute("post", new PostDTO());
            return "post-form";
        }
        model.addAttribute("message", "Vous devez vous authentifier pour poster un article\n\n(indice : admin admin)");
        model.addAttribute("desiredPage", "createPost");
        return "signin";
    }

    @PostMapping("/createPostAction")
    public String createPostAction(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "post-form";
        }
        blogService.createPost(post);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String getPostBydId(@PathVariable UUID id, Model model){
        model.addAttribute("post", blogService.getPostById(id));
        model.addAttribute("comment", new CommentDTO());
        return "post";
    }

    @GetMapping("/editPost/{id}")
    public String editForm(@PathVariable UUID id, Model model){
        if (signinService.isAuthorized()){
            PostDTO post = blogService.getPostById(id);
            if (post != null){
                model.addAttribute("post", post);
                return "post-form";
            }
            return "error/400";
        }
        model.addAttribute("message", "Vous devez vous authentifier pour modifier un article\n\n(indice : admin admin)");
        model.addAttribute("desiredPage", "editPost/"+id);
        return "signin";
    }

    @PostMapping("/editAction")
    public String editAction(@Valid @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "post-form";
        }
        post = blogService.updatePost(post);
        if (post != null){
            model.addAttribute("post", post);
            return "post";
        }
        return "error/400";
    }
}
