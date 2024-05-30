package com.lyonguyen.news.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lyonguyen.news.enums.Subject;
import com.lyonguyen.news.models.Article;
import com.lyonguyen.news.models.News;
import com.lyonguyen.news.services.ArticlesService;
import com.lyonguyen.news.services.NewsService;
import com.lyonguyen.news.services.SecurityService;

@Controller
public class ViewController {

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private NewsService newsService;
    
    
    @Autowired
    private SecurityService securityService;
    

	@GetMapping("/")
	public String home(Model model, @RequestParam(name = "search", required = false) String searchKey,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		
		
		
		Page<News> newsStream;
		Iterable<News> latestNews = newsService.getLatestNews();
		model.addAttribute("latestNews", latestNews);

		if (searchKey != null) {

			newsStream = newsService.searchNews(searchKey, page - 1);
			model.addAttribute("newsStream", newsStream);

			return "search";
		}

		newsStream = newsService.getNewsPage(page - 1);
//		newsStream.forEach(news -> {
//			System.out.println("check sub: " + news.getSubject());
//		});
		model.addAttribute("newsStream", newsStream);
		
		
		String checkRole = securityService.checkRole();
		model.addAttribute("roles", checkRole);

		return "home";
	}

    
;
    
    @GetMapping("/{Subject}")
    public String home1(Model model,
    		  @RequestParam(name = "search", required = false) String searchKey,
              @RequestParam(name = "page", defaultValue = "1") int page,
    		@PathVariable("Subject") String subject) {
    	
    	 Page<News> newsStream;
         Iterable<News> latestNews = newsService.getLatestNews();
         model.addAttribute("latestNews", latestNews);

         if (searchKey != null) {

             newsStream = newsService.searchNews(searchKey, page - 1);
             model.addAttribute("newsStream", newsStream);

             return "search";
         }
         
         System.out.println(subject);
         System.out.println(page);
         

         newsStream = newsService.getPageForSubject(page - 1, subject);
//         newsStream.forEach(news -> {
// 			System.out.println("check sub 222: " + news.getSubject());
// 		});
         
        model.addAttribute("newsStream", newsStream);
        
        
		String checkRole = securityService.checkRole();
		model.addAttribute("roles", checkRole);

        return "home";
    }

    @GetMapping("/article/{id}")
    public String getArticle(Model model,
                             @PathVariable("id") Long id) {

        Article article = articlesService.get(id);
        Iterable<News> latestNews = newsService.getLatestNews();
        
        String checkRole = securityService.checkRole();
        
        
		model.addAttribute("roles", checkRole);
        model.addAttribute("article", article);
        model.addAttribute("latestNews", latestNews);

        return "article";
    }

    @GetMapping("editor")
    public String createArticle(Model model) {
    	
    	String checkRole = securityService.checkRole();
    	
		model.addAttribute("roles", checkRole);
        model.addAttribute("article", new Article());
        model.addAttribute("subjects", Subject.getAllSubjectWithSpace());


        return "editor";
    }

    @GetMapping("editor/{id}")
    public String editArticle(Model model,
                              @PathVariable("id") Long id) {
        Article article = articlesService.get(id);
        
        String checkRole = securityService.checkRole();
        
		model.addAttribute("roles", checkRole);
        model.addAttribute("article", article);
        model.addAttribute("subjects", Subject.getAllSubjectWithSpace());


        return "editor";
    }
}
