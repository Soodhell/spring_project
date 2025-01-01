package com.example.demo.registrationInEvent.services;

import com.example.demo.news.models.News;
import com.example.demo.news.services.NewsService;
import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import com.example.demo.registrationInEvent.repositories.RegistrationInEventRepository;
import com.example.demo.users.models.User;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class RegistrationInEventService {

    private RegistrationInEventRepository registrationInEventRepository;
    private UserService userService;
    private NewsService newsService;

    public void save(String mail, Long id){
        registrationInEventRepository.save(new RegistrationInEventModel(userService.getUser(mail).get(), newsService.getNew(id)));
    }

    public List<RegistrationInEventModel> get(String mail){
        return registrationInEventRepository.findAllByUserMail(mail);
    }

}
