package com.example.demo.controller.users;


import com.example.demo.model.registrationSection.RegistrationSection;
import com.example.demo.model.sections.Sections;
import com.example.demo.model.users.User;
import com.example.demo.representationObjects.registration.RepresentationRegistrationSections;
import com.example.demo.representationObjects.sections.RepresentationSections;
import com.example.demo.representationObjects.users.PerformanceUsers;
import com.example.demo.representationObjects.users.RepresentationUsers;
import com.example.demo.service.registrationSection.RegistrationSectionService;
import com.example.demo.service.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Data
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private RegistrationSectionService registrationSectionService;

    @GetMapping("/update/{mail}")
    public PerformanceUsers update(@PathVariable("mail") String mail){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        if(mailUser.equals(mail)){
            Optional<User> userOptional = userService.getUser(mail);

            if (userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return RepresentationUsers.getPerformanceUsers(userService.getUser(mail).get());
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }

    @PatchMapping("/api/update/{mail}")
    public void pUpdate(@PathVariable String mail, HttpServletRequest request){

        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if(mailUser.equals(mail)){

            Optional<User> userOptional = userService.getUser(mail);


            if(userOptional.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
            }

            User user = userOptional.get();

            userService.set(user.getMail(), user.getPassword(), request.getParameter("firstname"), request.getParameter("lastname"), "USER");
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/api/delete/{mail}")
    public void delete(@PathVariable("mail") String mail){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (mailUser.equals(mail))
            userService.delete(mail);
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/get-user/{mail}")
    public Object[] getUser(@PathVariable String mail){
        if(userService.getUser(mail).isPresent()) {

                Object[] objects = new Object[2];
                objects[0] = RepresentationRegistrationSections.selectAll(registrationSectionService.getFindAll(mail));
                objects[1] = RepresentationUsers.getPerformanceUsers(userService.getUser(mail).get());

                return objects;
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
