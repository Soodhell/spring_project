package com.example.demo.registrationInEvent.controllers;

import com.example.demo.news.services.NewsService;
import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import com.example.demo.registrationInEvent.services.RegistrationInEventService;
import com.example.demo.representationObjects.registrationInEvent.PerformanceRegistration;
import com.example.demo.representationObjects.registrationInEvent.RepresentationRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@CrossOrigin
public class RegistrationInEventController {

    private RegistrationInEventService registrationInEventService;
    private NewsService newsService;

    @GetMapping("/news_reg/list/{mail}")
    public List<PerformanceRegistration> listReg(@PathVariable String mail){
        return RepresentationRegistration.getPerformanceRegistration(registrationInEventService.get(mail));
    }

    @PutMapping("/news_reg/{id}")
    public HttpStatus addReg(@PathVariable Long id){

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        if(newsService.getNew(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found");
        };

        List<RegistrationInEventModel> list = registrationInEventService.get(user);

        for(RegistrationInEventModel registrationInEventModel : list){
            if(registrationInEventModel.getId().equals(id)){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "News already exists");
            }
        }

        registrationInEventService.save(user, id);

        return HttpStatus.OK;
    }

    @DeleteMapping("/news_reg/delete/{id}")
    public HttpStatus delReg(@PathVariable Long id){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        registrationInEventService.delete(user, id);

        return HttpStatus.OK;
    }

}
