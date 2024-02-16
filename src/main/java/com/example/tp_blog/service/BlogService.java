package com.example.tp_blog.service;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;

import java.util.List;

public interface BlogService {
    Post createPost(Post post);
    List<Post> getPosts();
    Post getPostById(int id);
    Comment createComment(Comment comment);
}
