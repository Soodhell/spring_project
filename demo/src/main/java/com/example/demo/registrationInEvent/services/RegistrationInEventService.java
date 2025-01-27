package com.example.demo.registrationInEvent.services;

import com.example.demo.news.models.News;
import com.example.demo.news.services.NewsService;
import com.example.demo.registrationInEvent.models.RegistrationInEventModel;
import com.example.demo.registrationInEvent.repositories.RegistrationInEventRepository;
import com.example.demo.users.models.User;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class RegistrationInEventService {

    private RegistrationInEventRepository registrationInEventRepository;
    private UserService userService;
    private NewsService newsService;
    private final Map<Long, RegistrationInEventModel> registrationMap = new HashMap<>();

    public void save(String mail, Long id){
        registrationInEventRepository.save(new RegistrationInEventModel(userService.getUser(mail).get(), newsService.getNew(id)));
    }

    public void delete(String mail, Long id){
        registrationInEventRepository.deleteAllByNews_id(mail, id);
    }

    //Это не должно так работать, надо придумать как это можно захешировать, но пока идей нет

    public List<RegistrationInEventModel> get(String mail){
        return registrationInEventRepository.findAllByUserMail(mail);
    }

}
