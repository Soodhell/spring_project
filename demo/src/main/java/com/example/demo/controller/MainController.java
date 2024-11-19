package com.example.demo.controller;

import com.example.demo.service.users.ModifacationUserDetailsService;
import jakarta.servlet.ServletConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@AllArgsConstructor
public class MainController {

    private final ServletConfig servletConfig;
    private ModifacationUserDetailsService modifacationUserDetailsService;
    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping("/")
    public String index(Model model) {
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
