package com.example.demo.users.controllers;


import com.example.demo.classDTO.users.AuthDTO;
import com.example.demo.classDTO.users.LoginDTO;
import com.example.demo.config.configSecurity.JWTGenerator;
import com.example.demo.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Security;

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public LoginController(
            AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

//    @PostMapping("/api/post/login")
//    public ResponseEntity<AuthDTO> templogin(HttpServletRequest http) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        http.getParameter("username"),
//                        http.getParameter("password")
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        return new ResponseEntity<>(new AuthDTO(token), HttpStatus.OK);
//
//    }
//
//    @GetMapping("/api/login")
//    public String login() {
//        return "<!doctype html>\n" +
//                "<html lang=\"en\">\n" +
//                "    <head>\n" +
//                "        <meta charset=\"UTF-8\">\n" +
//                "        <meta name=\"viewport\"\n" +
//                "              content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
//                "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
//                "        <link rel=\"stylesheet\" href=\"/css/style.css\"/>\n" +
//                "        <title>Вход</title>\n" +
//                "    </head>\n" +
//                "    <body>\n" +
//                "        <form action=\"/api/post/login\" method=\"post\">\n" +
//                "            <input type=\"text\" name=\"username\">\n" +
//                "            <input type=\"password\" name=\"password\">\n" +
//                "            <input type=\"submit\">\n" +
//                "        </form>\n" +
//                "    </body>\n" +
//                "</html>";
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> loginPath(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthDTO(token), HttpStatus.OK);

    }

}
