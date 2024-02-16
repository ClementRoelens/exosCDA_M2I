package com.example.tp_blog.controller.web;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.impl.BlogServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private final BlogServiceImpl blogService;

    public CommentController(BlogServiceImpl blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/postComment/{postId}")
    public String postComment(@ModelAttribute("comment") Comment comment, @PathVariable int postId, Model model){
        Post post = blogService.getPostById(postId);
        comment.setAttachedPost(post);
        blogService.createComment(comment);
        model.addAttribute("post", post);
        return "post";
    }
}
