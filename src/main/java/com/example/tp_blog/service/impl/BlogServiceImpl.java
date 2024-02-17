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
        this.posts = new ArrayList<>(List.of(
                new Post(postCount++,
                        "C# mieux que Java ?",
                        "Bon c'est juste une question hein...",
                        "Mais tout de même c'est cool d'avoir des delegate, des passages par référence, des paramètres de retour et tout.\n" +
                                "Puis sérieux le Pascal Case c'est plus agréable, vous trouvez pas ?",
                        new ArrayList<>()),
                new Post(postCount++,
                        "Java mieux que C# ?",
                        "Ha ha, c'est la même question mais dans l'autre sens!",
                        "Bon ok, y'a des trucs qui manquent... Ok ok" +
                                "Mais Spring c'est quand même plus facile à configurer qu'ASP.NET !",
                        new ArrayList<>()),
                new Post(postCount++,
                        "David Fincher, une arnaque",
                        "Les pires films du réal le plus surcôté",
                        "Seven : je me suis endormi devant. Et putain c'est sacrément rare !\n" +
                                "Alien 3 : lui-même ne l'assume pas mdr\n" +
                                "Fight Club : ok ça c'est pas mal, même si c'est bon c'est très surcôté aussi\n" +
                                "Zodiac : UN DES PIRES FILMS QUE J'AI VU DE MA VIE ! C'est pas un film, c'est un Faites entrer l'accusé avec un peu de style\n" +
                                "Gone girl : ça le sauve ça, parce que franchement c'est vraiment cool :)",
                        new ArrayList<>())
        ));
    }

    @Override
    public Post createPost(Post post) {
        if (post.getContent().isEmpty() || post.getTitle().isEmpty() || post.getDescription().isEmpty()) {
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
