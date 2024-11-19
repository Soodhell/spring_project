package com.example.demo.controller.news;

import com.example.demo.repository.users.UserRepository;
import com.example.demo.service.news.NewsService;
import com.example.demo.service.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Data
@AllArgsConstructor
public class NewsController {

    private NewsService newsService;
    private UserService userService;

    @GetMapping("/news/set/{id}")
    public String newsSet(@PathVariable Long id, Model model) {
        model.addAttribute("new", newsService.getNew(id));
        return "news/set";
    }

    @PostMapping("/news/api/set/{id}")
    public String newsSet(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletRequest http) {
        newsService.setNews(
                id,
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                newsService.getNew(id).getAuthor().getMail()
        );
        return "redirect:/news/get/" + id;

    }

    @GetMapping("/news/get/{id}")
    public String newsGet(@PathVariable Long id, Model model) {
        model.addAttribute("new", newsService.getNew(id));
        return "news/get";
    }

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

        newsService.save(
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                userService.getUser(userName).get()
        );

        return "redirect:/";
    }

}
