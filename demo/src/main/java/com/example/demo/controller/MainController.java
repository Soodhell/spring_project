package com.example.demo.controller;

import com.example.demo.service.ModifacationUserDetailsService;
import com.example.demo.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
@AllArgsConstructor
public class MainController {

    private final ServletConfig servletConfig;
    private ModifacationUserDetailsService modifacationUserDetailsService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/")
    public String rAdmin() {
        return "redirect:/admin";
    }

}
