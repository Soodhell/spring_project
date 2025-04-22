package com.example.demo.sections.services;

import com.example.demo.sections.models.Sections;
import com.example.demo.sections.repositories.SectionsRepository;
import com.example.demo.users.repositories.UserRepository;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Data
@AllArgsConstructor
public class SectionsService {
    private final UserService userService;
    private SectionsRepository sectionsRepository;
    private UserRepository userRepository;

    private final Map<Long, Sections> sections = new HashMap<>();

    public Sections getSections(Long id){
        if(sections.isEmpty()) loadSections();
        return sections.get(id);
    }

    public Map<Long, Sections> getSections(){
        if(sections.isEmpty()) loadSections();
        return sections;
    }

    public void setSections(Long id, String title, String content, MultipartFile file, String mail) {
        if(sections.isEmpty()) loadSections();

        Sections section = sectionsRepository.getReferenceById(id);

        section.setTitle(title);
        section.setContent(content);
        new File(section.getPathImg()).deleteOnExit();
        section.setTeacher(userRepository.findByMail(mail).get());

        String nameFile = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            file.transferTo(new File("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile));
            section.setPathImg("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile);
            section.setNameImg(nameFile);
        }catch (Exception e){
            System.out.println("Проблема с файлом");
            return;
        }

        sectionsRepository.save(section);
        this.sections.put(section.getId(), section);

    }

    public Map<Long, Sections> selectAll() {
        if(sections.isEmpty()) loadSections();
        return sections;
    }

    public void save(String title, String content, MultipartFile file, String mail){
        if(sections.isEmpty()) loadSections();

        Sections section = new Sections();
        section.setTitle(title);
        section.setContent(content);
        section.setTeacher(userService.getUser(mail).get());

        String nameFile = UUID.randomUUID().toString() + file.getOriginalFilename();
        try {
            file.transferTo(new File("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile));
            section.setPathImg("/home/roman/shool/spring_project/demo/src/main/resources/img/" + nameFile);
            section.setNameImg(nameFile);
        }catch (Exception e){
            System.out.println("Проблема с файлом");
            return;
        }

        sectionsRepository.save(section);
        this.sections.put(section.getId(), section);
    }

    private void loadSections(){
        for(Sections item : sectionsRepository.findAll()){
            sections.put(item.getId(), item);
        }
    }
}
