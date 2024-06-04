package com.lyonguyen.news.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.History;
import com.lyonguyen.news.models.News;
import com.lyonguyen.news.models.NewsImpl;
import com.lyonguyen.news.models.User;
import com.lyonguyen.news.repositories.HistoryRepository;
import com.lyonguyen.news.repositories.UsersRepository;


@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

	@Autowired
    private HistoryRepository historyRepository;
	
    @Value("${webapp.pagesize}")
    private int pageSize;


    @Override
    public List<Article> getHistoryArticles(User user) {
        // Lấy danh sách lịch sử của người dùng
        List<History> userHistory = historyRepository.findByUser(user);
        

        // Lọc ra danh sách các bài viết từ danh sách lịch sử
        List<Article> historyArticles = userHistory.stream()
                .map(History::getArticle)
                .collect(Collectors.toList());

        return historyArticles;
    }
    
    @Override
    public Page<News> getHistoryArticles(User user, int page) {
        // Lấy danh sách lịch sử của người dùng
        List<History> userHistory = historyRepository.findByUser(user);
        
        // Lọc ra danh sách các bài viết từ danh sách lịch sử
        List<Article> historyArticles = userHistory.stream()
                .map(History::getArticle)
                .collect(Collectors.toList());

        // Phân trang dữ liệu
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("time").descending());
        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > historyArticles.size() ? historyArticles.size() : (start + pageable.getPageSize());

        // Tạo một danh sách mới chứa các đối tượng News
        List<News> newsList = historyArticles.subList(start, end).stream()
                .map(this::convertArticleToNews)
                .collect(Collectors.toList());

        // Tạo một Page từ danh sách News và trả về
        return new PageImpl<>(newsList, pageable, historyArticles.size());
    }

    
    @Override
    public void addHistory(User user, Article article) {
        // Kiểm tra xem lịch sử đã tồn tại hay không
        History existingHistory = historyRepository.findByUserAndArticle(user, article);

        // Nếu lịch sử đã tồn tại, cập nhật thời gian xem mới nhất
        if (existingHistory != null) {
            existingHistory.setViewedAt(LocalDateTime.now());
            historyRepository.save(existingHistory);
        } else { // Nếu không, tạo mới lịch sử
            History newHistory = new History(user, article, LocalDateTime.now());
            historyRepository.save(newHistory);
        }
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

}
