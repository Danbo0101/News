package com.lyonguyen.news.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.domain.Sort;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.News;
import com.lyonguyen.news.models.NewsImpl;
import com.lyonguyen.news.repositories.ArticlesRepository;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Value("${webapp.pagesize}")
    private int pageSize;

    @Override
    public Page<News> getNewsPage(int page) {
        @SuppressWarnings("deprecation")
        Pageable pageable = new QPageRequest(page, pageSize);

        Page<News> news = articlesRepository.findAllByOrderByTimeDesc(pageable);

        Assert.isTrue(page < news.getTotalPages() || page == 0, "Except limit page!");

        return news;
    }
    

    
    @Override
    public Page<News> getPageForSubject(int page, String subject) {
    	
    	String converted = subject.replace("_", " ");
    	
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("time").descending());

        Iterable<Article> articles = articlesRepository.findAll();

        List<News> filteredNews = StreamSupport.stream(articles.spliterator(), false)
                .filter(article -> converted.equals(article.getSubject()))
                .map(this::convertArticleToNews)
                .collect(Collectors.toList());

//        filteredNews.forEach(ar -> {
//            System.out.println("check sub1111: " + ar.getSubject());
//        });

        // Create a Page object using the filtered news list and pageable
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > filteredNews.size() ? filteredNews.size() : (start + pageable.getPageSize());
        Page<News> newsPage = new PageImpl<>(filteredNews.subList(start, end), pageable, filteredNews.size());

        if (page >= newsPage.getTotalPages() && page != 0) {
            throw new IllegalArgumentException("Page number exceeds available pages.");
        }

        return newsPage;
    }

    

    @Override
    public Iterable<News> getLatestNews() {
        return articlesRepository.findTop5ByOrderByTimeDesc();
    }

    @Override
    public Page<News> searchNews(String key, int page) {
        @SuppressWarnings("deprecation")
        Pageable pageable = new QPageRequest(page, pageSize);

        Page<News> news;

        if (key.trim().isEmpty()) {
            news = Page.empty(pageable);
        } else {
            news = articlesRepository.findByTitleLike("%" + key + "%", pageable);
        }

        Assert.isTrue(page < news.getTotalPages() || page == 0, "Except limit page!");

        return news;
    }
    
    private News convertArticleToNews(Article article) {
        NewsImpl news = new NewsImpl();
        news.setId(article.getId());
        news.setTitle(article.getTitle());
        news.setSubject(article.getSubject());
        news.setTime(article.getTime());
        news.setImage(article.getImage());
        news.setBrief(article.getBrief());
        return news;
    }

    
    
    

    
//    @Override
//    public Page<News> searchNewsBySubject(String keyWord, int page, int pageSize) {
//        String[] listSubject = Subject.getAllSubject();
//        String[] arr = new String[8]; // Create an array with size 8
//
//        for (int i = 0; i <= 7; i++) {
//            arr[i] = listSubject[i];
//        }
//
//        List<News> matchedNews = new ArrayList<>();
//
//        for (News news : allNews) {
//            if (Arrays.asList(arr).contains(news.getSubject())) {
//                matchedNews.add(news);
//            }
//        }
//
//        int start = page * pageSize;
//        int end = Math.min(start + pageSize, matchedNews.size());
//        List<News> pageContent = matchedNews.subList(start, end);
//
//        return new PageImpl<>(pageContent, PageRequest.of(page, pageSize), matchedNews.size());
//    }

}
