package org.example.classes;

import java.util.HashMap;

public class ArticleService {

    private static HashMap<Integer, Article> articles = new HashMap<>();

    public static void addArticle(Article article){
        articles.put(article.getId(), article);
    }

    public static HashMap<Integer, Article> getAllArticles() {
        return articles;
    }

    public static Article getOneArticle(int articleId){
        return articles.get(articleId);
    }
}
