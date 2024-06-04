package com.lyonguyen.news.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
public class History {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(name = "viewed_at")
    private LocalDateTime viewedAt;

    public History() {}

    public History(User user, Article article, LocalDateTime viewedAt) {
        this.user = user;
        this.article = article;
        this.viewedAt = viewedAt;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public LocalDateTime getViewedAt() {
		return viewedAt;
	}

	public void setViewedAt(LocalDateTime viewedAt) {
		this.viewedAt = viewedAt;
	}
}
