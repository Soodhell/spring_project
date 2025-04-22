package com.example.demo.registration.controllers;

import com.example.demo.registration.services.RegistrationSectionService;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@AllArgsConstructor
@CrossOrigin
public class RegistrationSectionController {

    private RegistrationSectionService registrationSectionService;
    private UserService userService;

    @PutMapping("/registration-section/add/{id}")
    public void addRegistrationSection(@PathVariable Long id) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        registrationSectionService.add(id, userName);
    }

}
