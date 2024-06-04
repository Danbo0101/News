package com.lyonguyen.news.repositories;

import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.History;
import com.lyonguyen.news.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUser(User user);
    // Tìm kiếm lịch sử bằng id của người dùng và id của bài viết
    History findByUserIdAndArticleId(Long userId, Long articleId);

    // Tìm kiếm lịch sử bằng đối tượng User và đối tượng Article
    History findByUserAndArticle(User user, Article article);
}
