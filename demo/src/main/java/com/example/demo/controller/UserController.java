package com.example.demo.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@AllArgsConstructor
public class UserController {
    @GetMapping("/all-user")
    public String allUser() {
        return "all-user";
    }

    @GetMapping("/all-user/")
    public String rAllUser() {
        return "redirect:/all-user";
    }
}
