package com.example.tp_blog.service.impl;

import com.example.tp_blog.dto.CommentDTO;
import com.example.tp_blog.dto.PostDTO;
import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.repository.CommentRepository;
import com.example.tp_blog.repository.PostRepository;
import com.example.tp_blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public BlogServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostDTO createPost(PostDTO post) {
        try {
            postRepository.save(post.toPost());
        } catch (Exception e) {
            System.out.println(e);
            post = null;
        }

        return post;
    }

    @Override
    public List<PostDTO> getPosts() {
        List<PostDTO> postsDTO = new ArrayList<>();

        try {
            postsDTO = postRepository.findAll().stream().map(Post::toDTO).toList();
        } catch (Exception e) {
            System.out.println(e);
        }

        return postsDTO;
    }

    @Override
    public List<CommentDTO> getComments() {
        List<CommentDTO> commentDTOS = new ArrayList<>();

        try {
            commentDTOS = commentRepository.findAll().stream().map(Comment::toDTO).toList();
        } catch (Exception e) {
            System.out.println(e);
        }

        return commentDTOS;
    }

    @Override
    public PostDTO getPostById(UUID id) {
        Post post = null;
        try {
            post =  postRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (post != null){
            return post.toDTO();
        }
        return null;
    }

    @Override
    public CommentDTO getCommentById(UUID id) {
        Comment comment = null;
        try {
            comment = commentRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (comment != null){
            return comment.toDTO();
        }
        return null;
    }

    @Override
    public PostDTO updatePost(PostDTO post) {
        try {
            postRepository.save(post.toPost());
            return post;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public CommentDTO updateComment(CommentDTO comment) {
        try {
            commentRepository.save(comment.toComment());
            return comment;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean deletePost(PostDTO post) {
        try {
            postRepository.delete(post.toPost());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteComment(CommentDTO comment) {
        try {
            commentRepository.delete(comment.toComment());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, PostDTO attachedPost) {
          try {
            attachedPost.toPost().addComment(commentDTO.toComment());
            postRepository.save(attachedPost.toPost());
            Comment comment = commentDTO.toComment();
              comment.setAttachedPost(attachedPost.toPost());
            commentRepository.save(comment);
            return commentDTO;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
