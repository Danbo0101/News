package com.lyonguyen.news.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.services.ArticlesService;

@RestController
@RequestMapping("/api/article")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;
    
    @GetMapping
    public ResponseEntity<List<Article>> getArticlesBySubject(@RequestParam("subject") String subject) {
        List<Article> articles = articlesService.getArticlesBySubject(subject);
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody Article article) {

        Article savedArticle = articlesService.create(article);
        
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("article").path("/{id}")
                .buildAndExpand(savedArticle.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@RequestBody Article article,
                                       @PathVariable("id") Long id) {
        article.setId(id);

        articlesService.update(article);
        
        System.out.println("URL anh");

        System.out.println(article.getImage());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("article").path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable("id") Long id) {

        articlesService.delete(id);

        return ResponseEntity.noContent().build();

    }

}
