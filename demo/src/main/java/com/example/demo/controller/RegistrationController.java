package com.example.demo.controller;


import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/register/")
    public String rRegister() {
        return "redirect:/register";
    }

    @PostMapping("/api/register")
    public String register(HttpServletRequest request) {
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
