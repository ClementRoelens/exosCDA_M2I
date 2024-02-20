package com.example.tp_blog.controller.web;

import com.example.tp_blog.dto.CommentDTO;
import com.example.tp_blog.dto.PostDTO;
import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class CommentController {

    private final BlogServiceImpl blogService;

    public CommentController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/postComment/{postId}")
    public String postComment(@Valid @ModelAttribute("comment") CommentDTO comment, BindingResult bindingResult, @PathVariable UUID postId, Model model){
        PostDTO post = blogService.getPostById(postId);
        if (post != null){
            model.addAttribute("post", post);
            if (bindingResult.hasErrors()){
                StringBuilder errors = new StringBuilder();

                for (ObjectError o : bindingResult.getAllErrors()){
                    errors.append(o.toString());
                    errors.append("\n");
                }

                System.out.println(errors);
                return "post";
            }
            blogService.createComment(comment, post);
            post.addComment(comment);
            return "post";
        }
        return "error/400";
    }
}
