package com.example.demo.controller.registrationSection;

import com.example.demo.model.registrationSection.RegistrationSection;
import com.example.demo.service.registrationSection.RegistrationSectionService;
import com.example.demo.service.users.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Data
@AllArgsConstructor
public class RegistrationSectionController {

    private RegistrationSectionService registrationSectionService;
    private UserService userService;

    @PostMapping("/registration-section/add/{id}")
    public String addRegistrationSection(@PathVariable Long id) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        registrationSectionService.add(id, userName);

        return "redirect:/get-user/" + userName;
    }

}
