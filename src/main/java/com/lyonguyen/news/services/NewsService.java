package com.lyonguyen.news.services;

import com.lyonguyen.news.models.News;
import org.springframework.data.domain.Page;


public interface NewsService {

    Page<News> getNewsPage(int page);
    
    Page<News> getPageForSubject(int page, String subject);

    Iterable<News> getLatestNews();

    Page<News> searchNews(String key, int page);
    
//    Page<News> searchNewsBySubject(String keyWord, int page, int pageSize);
    
    
}
