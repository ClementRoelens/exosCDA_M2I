package com.example.tp_blog.service.impl;

import com.example.tp_blog.entity.Comment;
import com.example.tp_blog.entity.Post;
import com.example.tp_blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final List<Post> posts;
    private int postCount;
    private int commentCount;

    public BlogServiceImpl() {
        this.posts = new ArrayList<>();
    }

    @Override
    public Post createPost(Post post) {
        if (post.getContent().isEmpty() || post.getTitle().isEmpty() || post.getDescription().isEmpty()){
            return null;
        }
        post.setId(postCount++);
        posts.add(post);
        return post;
    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public Post getPostById(int id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Comment createComment(Comment comment) {
        for (Post post : posts) {
            if (post.getId() == comment.getAttachedPost().getId()) {
                comment.setId(commentCount++);
                post.addComment(comment);
                return comment;
            }
        }
        return null;
    }
}
