package com.example.demo.users.controllers;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@AllArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @GetMapping("/login/")
    public String rLogin() {
        return "redirect:/login";
    }
}
