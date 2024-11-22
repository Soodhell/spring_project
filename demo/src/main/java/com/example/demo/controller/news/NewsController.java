package com.example.demo.controller.news;

import com.example.demo.repository.users.UserRepository;
import com.example.demo.representationObjects.news.PerformanceNews;
import com.example.demo.representationObjects.news.RepresentationNews;
import com.example.demo.service.news.NewsService;
import com.example.demo.service.users.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Data
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UserService userService;

    @GetMapping("/news/set/{id}")
    public PerformanceNews newsSet(@PathVariable Long id, Model model) {
        if(!userService.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get()
                .getRoles()
                .equals("ADMIN")
        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return RepresentationNews.getNews(newsService.getNew(id));
    }

    @PostMapping("/news/api/set/{id}")
    public void newsSet(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletRequest http) {
        if(!userService.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get()
                .getRoles()
                .equals("ADMIN")
        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        newsService.setNews(
                id,
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                newsService.getNew(id).getAuthor().getMail()
        );
    }

    @GetMapping("/news/get/{id}")
    public PerformanceNews newsGet(@PathVariable Long id) {
        return RepresentationNews.getNews(newsService.getNew(id));
    }

//    @GetMapping("/news/add")
//    public String add(){
//        return "news/add";
//    }

    @PostMapping("/news/api/add")
    public void apiAdd(@RequestParam("file") MultipartFile file, HttpServletRequest http){
        if(!userService.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get()
                .getRoles()
                .equals("ADMIN")
        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        String userName = SecurityContextHolder
                .getContext()
                        .getAuthentication()
                                .getName();

        newsService.save(
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                userService.getUser(userName).get()
        );
    }

}
