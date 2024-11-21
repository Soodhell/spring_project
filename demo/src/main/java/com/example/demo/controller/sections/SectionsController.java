package com.example.demo.controller.sections;

import com.example.demo.service.news.NewsService;
import com.example.demo.service.sections.SectionsService;
import com.example.demo.service.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Data
@AllArgsConstructor
public class SectionsController {

    private SectionsService sectionsService;
    private UserService userService;

    @GetMapping("/section/set/{id}")
    public String sectionSet(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionsService.getSections(id));
        return "sections/set";
    }

    @PostMapping("/section/api/set/{id}")
    public String sectionSet(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletRequest http) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        sectionsService.setSections(
                id,
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                http.getParameter("teacher")
        );
        return "redirect:/section/get/" + id;

    }

    @GetMapping("/section/get/{id}")
    public String sectionGet(@PathVariable Long id, Model model) {
        model.addAttribute("section", sectionsService.getSections(id));
        return "sections/get";
    }

    @GetMapping("/section/add")
    public String add(){
        return "sections/add";
    }

    @PostMapping("/section/api/add")
    public String apiAdd(@RequestParam("file") MultipartFile file, HttpServletRequest http){

        sectionsService.save(
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                http.getParameter("teacher")
        );

        return "redirect:/";
    }

}
