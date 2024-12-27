package com.example.demo.users.controllers;


import com.example.demo.classDTO.users.RegisterDTO;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;


//    @GetMapping("/register")
//    public String register() {
//        return "users/register";
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {

        userService.add(
                registerDTO.getMail(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getFirst_name(),
                registerDTO.getLast_name(),
                "USER"
        );

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
