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
    private UserService userService;
    private ModifacationUserDetailsService modifacationUserDetailsService;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/")
    public String rAdmin() {
        return "redirect:/admin";
    }

    @GetMapping("/all-user")
    public String allUser() {
        return "all-user";
    }

    @GetMapping("/all-user/")
    public String rAllUser() {
        return "redirect:/all-user";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/api/login")
    public String login(HttpServletRequest request) {
        modifacationUserDetailsService.loadUserByUsername(request.getParameter("username"));
        return "redirect:/all-user";
    }

    @PostMapping("/api/register")
    public String registerUser(HttpServletRequest request) {
        userService.add(
                request.getParameter("mail"),
                request.getParameter("password"),
                request.getParameter("first_name"),
                request.getParameter("last_name"),
                "USER"
        );
        return "redirect:/login";
    }

}
