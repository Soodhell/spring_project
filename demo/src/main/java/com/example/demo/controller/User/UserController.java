package com.example.demo.controller.User;


import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Data
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/update/{mail}")
    public String update(@PathVariable("mail") String mail, Model model){
        String mailUser = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if(mailUser.equals(mail)){
            return "user/update";
        }

        return "redirect:/";

    }

    @GetMapping("/get-user/{mail}")
    public String getUser(@PathVariable String mail, Model model){
        if(userService.getUser(mail).isPresent()) model.addAttribute("user",userService.getUser(mail).get());
            else model.addAttribute("user",null);
        return "user/get-user";
    }

    @GetMapping("/all-user")
    public String allUser() {
        return "user/all-user";
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
