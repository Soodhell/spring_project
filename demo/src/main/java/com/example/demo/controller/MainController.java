package com.example.demo.controller;

import com.example.demo.model.news.News;
import com.example.demo.representationObjects.news.PerformanceNews;
import com.example.demo.representationObjects.news.RepresentationNews;
import com.example.demo.service.news.NewsService;
import com.example.demo.service.users.ModifacationUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.core.JSONCFormat;
import jakarta.servlet.ServletConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public Map<Long, PerformanceNews> index() {
        return RepresentationNews.selectAll(newsService.selectAll());
    }

//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }
//
//    @GetMapping("/admin/")
//    public String rAdmin() {
//        return "redirect:/admin";
//    }

}
