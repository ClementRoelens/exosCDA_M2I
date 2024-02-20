package com.example.tp_blog.service;

import com.example.tp_blog.dto.CommentDTO;
import com.example.tp_blog.dto.PostDTO;
import com.example.tp_blog.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    PostDTO createPost(PostDTO post);
    CommentDTO createComment(CommentDTO comment, PostDTO attachedPost);
    List<PostDTO> getPosts();
    List<CommentDTO> getComments();
    PostDTO getPostById(UUID id);
    CommentDTO getCommentById(UUID id);
    PostDTO updatePost(PostDTO post);
    CommentDTO updateComment(CommentDTO comment);
    boolean deletePost(PostDTO post);
    boolean deleteComment(CommentDTO comment);
}
