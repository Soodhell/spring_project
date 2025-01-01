package com.example.demo.registrationInEvent.controllers;

import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import com.example.demo.registrationInEvent.services.RegistrationInEventService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
public class RegistrationInEventController {

    private RegistrationInEventService registrationInEventService;

    @GetMapping("test_event")
    public List<RegistrationInEventModel> testEvent(){
        // Осталось допилить то, каким образом это должно возращаться
    }

}
