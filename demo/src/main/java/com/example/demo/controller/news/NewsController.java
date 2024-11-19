package com.example.demo.controller.news;

import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.news.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Data
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UserRepository userRepository;

    @GetMapping("/news/add")
    public String add(){
        return "news/add";
    }

    @PostMapping("/news/api/add")
    public String apiAdd(@RequestParam("file") MultipartFile file, HttpServletRequest http){

        String userName = SecurityContextHolder
                .getContext()
                        .getAuthentication()
                                .getName();

        newsService.save(http.getParameter("title"), http.getParameter("content"), file, userRepository.findByMail(userName).get());

        return "redirect:/";
    }

}
