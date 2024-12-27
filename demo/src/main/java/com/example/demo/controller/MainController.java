package com.example.demo.controller;

import com.example.demo.representationObjects.news.PerformanceNews;
import com.example.demo.representationObjects.news.RepresentationNews;
import com.example.demo.news.services.NewsService;
import com.example.demo.users.services.ModifacationUserDetailsService;
import jakarta.servlet.ServletConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
public class MainController {

    private final ServletConfig servletConfig;
    private ModifacationUserDetailsService modifacationUserDetailsService;
    @Autowired
    SessionRegistry sessionRegistry;
    private NewsService newsService;

    @GetMapping("/")
    public List<PerformanceNews> index() {
        return RepresentationNews.selectAll(newsService.selectAll());
    }
}
