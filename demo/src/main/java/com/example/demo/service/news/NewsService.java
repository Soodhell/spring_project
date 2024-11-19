package com.example.demo.service.news;

import com.example.demo.model.news.News;
import com.example.demo.model.users.User;
import com.example.demo.repository.news.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NewsService {

    private NewsRepository newsRepository;


    public List<News> selectAll() {
        return newsRepository.findAll();
    }

    public void save(String title, String content, MultipartFile file, User user){

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setAuthor(user);

        String nameFile = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            file.transferTo(new File("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile));
            news.setPathImg("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile);
            news.setNameImg(nameFile);
        }catch (Exception e){
            System.out.println("Проблема с файлом");
            return;
        }

        newsRepository.save(news);
    }

}
