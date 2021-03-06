package com.chicago.server.controller;

import com.chicago.server.domain.News;
import com.chicago.server.repository.NewsRepository;
import com.chicago.server.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.Random;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
@Slf4j
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    /**
     * 뉴스 조회 Controller
     */
    @RequestMapping("news")
    public List<News> newsView(@PageableDefault Pageable pageable){
        List<News> newsList = newsService.loadNews(0, 10, "writtenTime");

        System.out.println("hihi");

        return newsList;
    }

    /**
     * 뉴스 삽입 Controller
     */
    @PostMapping("news")
    public String newPost(@RequestBody NewsPostForm newsPostForm){
        int minNum = 83;
        int maxNum = 100;
        int reliability = new Random().nextInt((maxNum - minNum) + 1) + minNum;
        News news = new News();
        news.setWriter(newsPostForm.getWriter());
        news.setContent(newsPostForm.getContent());
        news.setTitle(newsPostForm.getTitle());
        news.setReliability(reliability);
        newsService.enroll(news);
        return "success to insert";
    }
}
