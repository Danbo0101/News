package com.lyonguyen.news.models;

import java.sql.Timestamp;

public class NewsImpl implements News {
    private Long id;
    private String title;
    private String subject;
    private Timestamp time;
    private String image;
    private String brief;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public Timestamp getTime() {
        return time;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getBrief() {
        return brief;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void setBrief(String brief) {
        this.brief = brief;
    }
}

