package com.example.demo.news.controllers;

import com.example.demo.classDTO.news.NewsAddDTO;
import com.example.demo.classDTO.news.NewsSetDTO;
import com.example.demo.representationObjects.news.PerformanceNews;
import com.example.demo.representationObjects.news.RepresentationNews;
import com.example.demo.news.services.NewsService;
import com.example.demo.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Data
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UserService userService;

//    @GetMapping("/news/set/{id}")
//    public PerformanceNews newsSet(@PathVariable Long id, Model model) {
//        if(!userService.getUser(
//                        SecurityContextHolder
//                                .getContext()
//                                .getAuthentication()
//                                .getName()
//                )
//                .get()
//                .getRoles()
//                .equals("ADMIN")
//        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//        return RepresentationNews.getNews(newsService.getNew(id));
//    }

    @PatchMapping("/news/set/{id}")
    public void newsSet(@PathVariable Long id, @RequestParam("file") MultipartFile file, @RequestBody NewsSetDTO newsSetDTO) {
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
                newsSetDTO.getTitle(),
                newsSetDTO.getContent(),
                file,
                newsService.getNew(id).getAuthor().getMail(),
                newsSetDTO.getType_news()
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

    @PutMapping("/news/add")
    public void apiAdd(@RequestParam("file") MultipartFile file, @RequestBody NewsAddDTO newsAddDTO){
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
                newsAddDTO.getTitle(),
                newsAddDTO.getContent(),
                file,
                userService.getUser(userName).get(),
                newsAddDTO.getType_news()
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
