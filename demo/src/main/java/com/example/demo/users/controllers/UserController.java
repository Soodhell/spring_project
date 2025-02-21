package com.example.demo.users.controllers;


import com.example.demo.classDTO.users.UpdateDTO;
import com.example.demo.users.models.User;
import com.example.demo.representationObjects.registration.RepresentationRegistrationSections;
import com.example.demo.representationObjects.users.PerformanceUsers;
import com.example.demo.representationObjects.users.RepresentationUsers;
import com.example.demo.registration.services.RegistrationSectionService;
import com.example.demo.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@Data
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private UserService userService;
    private RegistrationSectionService registrationSectionService;

    @PatchMapping("/update/{mail}")
    public ResponseEntity<String> update(@PathVariable String mail, @RequestBody UpdateDTO updateDTO){

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

            userService.set(
                    user.getMail(),
                    user.getPassword(),
                    updateDTO.getFirst_name(),
                    updateDTO.getLast_name(),
                    "USER"
            );

            return new ResponseEntity<>("User update success", HttpStatus.OK);

        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/delete/{mail}")
    public ResponseEntity<String>  delete(@PathVariable("mail") String mail){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (mailUser.equals(mail))
            userService.delete(mail);
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>("User delete success", HttpStatus.OK);
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
