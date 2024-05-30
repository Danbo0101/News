package com.lyonguyen.news.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyonguyen.news.exceptions.BadRequestException;
import com.lyonguyen.news.exceptions.NotFoundException;
import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.News;
import com.lyonguyen.news.repositories.ArticlesRepository;

@Service
@Transactional
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    public Article get(Long id) {
        Optional<Article> maybeArticle = articlesRepository.findById(id);

        if (!maybeArticle.isPresent()) {
            throw new NotFoundException();
        }

        return maybeArticle.get();
    }

    @Override
    public Article create(Article article) {
        if (article.getId() != null) {
            throw new BadRequestException();
        }

        return articlesRepository.save(article);
    }

    @Override
    public Article update(Article article) {
        if (article.getId() == null) {
            throw new BadRequestException();
        }
        if (!articlesRepository.existsById(article.getId())) {
            throw new NotFoundException();
        }
        if (article.getImage() == null || article.getImage().strip() == "")
        {
        	article.setImage(articlesRepository.findById(article.getId()).get().getImage());
        }

        return articlesRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new BadRequestException();
        }
        if (!articlesRepository.existsById(id)) {
            throw new NotFoundException();
        }

        articlesRepository.deleteById(id);
    }
    
    @Override
    public List<Article> getArticlesBySubject(String subject) {
        List<News> newsItems = StreamSupport.stream(articlesRepository.findBySubjectContaining(subject).spliterator(), false)
                                           .collect(Collectors.toList());

        // Convert the List<News> to a List<Article>
        return newsItems.stream()
                        .map(news -> {
                            Article article = new Article();
                            article.setId(news.getId());
                            article.setTitle(news.getTitle());
                            article.setSubject(news.getSubject());
                            article.setTime(news.getTime());
                            article.setImage(news.getImage());
                            article.setBrief(news.getBrief());
                            // Add any other necessary mapping
                            return article;
                        })
                        .collect(Collectors.toList());
    }
}
