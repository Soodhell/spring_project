package com.example.demo.news.controllers;

import com.example.demo.classDTO.news.NewsAddDTO;
import com.example.demo.classDTO.news.NewsSetDTO;
import com.example.demo.representationObjects.news.PerformanceNews;
import com.example.demo.representationObjects.news.RepresentationNews;
import com.example.demo.news.services.NewsService;
import com.example.demo.users.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Tag(name="news")
@RestController
@Data
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UserService userService;

    @PatchMapping("/news/set/{id}")
    public void newsSet(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type) {
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
                title,
                content,
                file,
                newsService.getNew(id).getAuthor().getMail(),
                Integer.parseInt(type)
        );
    }

    @GetMapping("/news/get/{id}")
    public PerformanceNews newsGet(@PathVariable Long id) {
        return RepresentationNews.getNews(newsService.getNew(id));
    }

    @PutMapping(path = "/news/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void apiAdd(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type){
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
                title,
                content,
                file,
                userService.getUser(userName).get(),
                Integer.parseInt(type)
        );
    }

    @DeleteMapping("/news/delete/{id}")
    public void Delete(@PathVariable Long id) {
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

        newsService.delete(id);

    }

}
