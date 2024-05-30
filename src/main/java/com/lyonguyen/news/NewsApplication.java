package com.lyonguyen.news;
import java.util.Arrays;


import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lyonguyen.news.utils.Converter;
import com.lyonguyen.news.utils.Random;
import com.lyonguyen.news.utils.Url;
import com.lyonguyen.news.utils.SubjectUtil;
import com.lyonguyen.news.enums.Subject;

@SpringBootApplication
public class NewsApplication {

	@Bean
	public Converter converter() {
		return new Converter();
	}

	@Bean
	public Url url() { return new Url(); }

	@Bean
    public Random random() { return new Random(); }

	public static void main(String[] args) throws InterruptedException{
		SpringApplication.run(NewsApplication.class, args);
	}
	
	
}
