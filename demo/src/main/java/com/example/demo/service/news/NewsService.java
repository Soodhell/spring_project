package com.example.demo.service.news;

import com.example.demo.model.news.News;
import com.example.demo.model.users.User;
import com.example.demo.repository.news.NewsRepository;
import com.example.demo.repository.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NewsService {

    private NewsRepository newsRepository;
    private UserRepository userRepository;

    private final Map<Long, News> news = new HashMap<>();

    public News getNew(Long id){
        if(news.isEmpty()) loadNews();
        return news.get(id);
    }

    public void setNews(Long id, String title, String content, MultipartFile file, String mail) {
        if(news.isEmpty()) loadNews();

        News news = newsRepository.getReferenceById(id);

        news.setTitle(title);
        news.setContent(content);
        new File(news.getPathImg()).deleteOnExit();
        news.setAuthor(userRepository.findByMail(mail).get());

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
        this.news.put(news.getId(), news);

    }

    public Map<Long, News> selectAll() {
        if(news.isEmpty()) loadNews();
        return news;
    }

    public void save(String title, String content, MultipartFile file, User user){
        if(news.isEmpty()) loadNews();

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
        this.news.put(news.getId(), news);
    }

    private void loadNews(){
        for(News item : newsRepository.findAll()){
            news.put(item.getId(), item);
        }
    }

}
