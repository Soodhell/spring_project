package com.example.demo.controller.users;


import com.example.demo.service.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

//    @GetMapping("/register")
//    public String register() {
//        return "users/register";
//    }

    @PutMapping("/api/register")
    public void register(HttpServletRequest request) {
        userService.add(
                request.getParameter("mail"),
                request.getParameter("password"),
                request.getParameter("first_name"),
                request.getParameter("last_name"),
                "USER"
        );
    }

}
