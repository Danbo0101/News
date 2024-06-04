package com.lyonguyen.news.services;

import java.util.List;


import org.springframework.data.domain.Page;

import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.History;
import com.lyonguyen.news.models.News;
import com.lyonguyen.news.models.User;

public interface HistoryService {

	List<Article> getHistoryArticles(User user);
	
	void addHistory(User user, Article article);
	
	Page<News> getHistoryArticles(User user, int page);
}
