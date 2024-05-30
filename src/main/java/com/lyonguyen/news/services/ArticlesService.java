package com.lyonguyen.news.services;

import java.util.List;

import com.lyonguyen.news.models.Article;

public interface ArticlesService {

    Article get(Long id);

    Article create(Article article);

    Article update(Article article);

    void delete(Long id);
    
    List<Article> getArticlesBySubject(String subject);
}
