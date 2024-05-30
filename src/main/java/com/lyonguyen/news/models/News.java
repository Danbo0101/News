package com.lyonguyen.news.models;

import java.sql.Timestamp;

public interface News {

    Long getId();

    String getTitle();

    String getSubject();

    Timestamp getTime();

    String getImage();

    String getBrief();

	void setId(Long id);

	void setTitle(String title);

	void setSubject(String subject);

	void setTime(Timestamp time);

	void setImage(String image);

	void setBrief(String brief);
}