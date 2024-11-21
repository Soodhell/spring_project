package com.example.demo.controller.users;


import com.example.demo.model.registrationSection.RegistrationSection;
import com.example.demo.model.sections.Sections;
import com.example.demo.model.users.User;
import com.example.demo.service.registrationSection.RegistrationSectionService;
import com.example.demo.service.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@Data
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private RegistrationSectionService registrationSectionService;

    @GetMapping("/update/{mail}")
    public String update(@PathVariable("mail") String mail, Model model){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        if(mailUser.equals(mail)){
            Optional<User> userOptional = userService.getUser(mail);

            if (userOptional.isEmpty()) return "redirect:/404";

            model.addAttribute("first_name", userOptional.get().getFirstName());
            model.addAttribute("last_name", userOptional.get().getLastName());
            model.addAttribute("mail",mail);

            return "users/update";
        }

        return "redirect:/";

    }

    @PostMapping("/api/update/{mail}")
    public String pUpdate(@PathVariable String mail, HttpServletRequest request){

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

            return "redirect:/get-user/"+mail;
        }

        return "redirect:/404";
    }

    @PostMapping("/api/delete/{mail}")
    public String delete(@PathVariable("mail") String mail){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        System.out.println(mailUser.equals(mail));

        if (mailUser.equals(mail))
            userService.delete(mail);
        else return "redirect:/404";

        return "redirect:/logout";
    }

    @GetMapping("/get-user/{mail}")
    public String getUser(@PathVariable String mail, Model model){
        if(userService.getUser(mail).isPresent()) {
            model.addAttribute("user",userService.getUser(mail).get());
            model.addAttribute("sections", registrationSectionService.getFindAll(mail));
        }
        else {
                model.addAttribute("user",null);
                model.addAttribute("sections", null);
        }
        return "users/get-user";
    }

    @GetMapping("/all-user")
    public String allUser() {
        return "users/all-user";
    }


    /*
    * =========================================================================================
    * =***************************************************************************************=
    * =**********                                                                   **********=
    * =**********                         ALL  REDIRECT                             **********=
    * =**********                                                                   **********=
    * =***************************************************************************************=
    * =========================================================================================
    */


    @GetMapping("/get-user/{mail}/")
    public String rGetUser(@PathVariable String mail){
        return "redirect:/get-user/" + mail;
    }

    @GetMapping("/all-user/")
    public String rAllUser() {
        return "redirect:/all-user";
    }
}
