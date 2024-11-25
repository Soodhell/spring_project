package com.example.demo.registration.services;

import com.example.demo.registration.models.RegistrationSection;
import com.example.demo.registration.repositories.RegistrationSectionRepository;
import com.example.demo.sections.services.SectionsService;
import com.example.demo.users.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class RegistrationSectionService {
    private RegistrationSectionRepository registrationSectionRepository;
    private UserService userService;
    private SectionsService sectionsService;

    private final Map<Long, RegistrationSection> registrationSections = new HashMap<>();

    public void add(Long id, String mail){
        if(registrationSections.isEmpty()) loadRegistrationSections();

        RegistrationSection registrationSection = new RegistrationSection();
        registrationSection.setSection(sectionsService.getSections(id));
        registrationSection.setUser(userService.getUser(mail).get());

        for (RegistrationSection registrationSection1 : registrationSectionRepository.findAll()) {
            if (registrationSection1.getUser().getMail().equals(mail))
                return;
        }

        registrationSectionRepository.save(registrationSection);
        registrationSections.put(id, registrationSection);
    }

    public RegistrationSection get(Long id){
        if(registrationSections.isEmpty()) loadRegistrationSections();
        return registrationSections.get(id);
    }

    public Map<Long, RegistrationSection> getFindAll(){
        if(registrationSections.isEmpty()) loadRegistrationSections();
        return registrationSections;
    }

    public Map<Long, RegistrationSection> getFindAll(String mail){
        if(registrationSections.isEmpty()) loadRegistrationSections();

        Map<Long, RegistrationSection> registrationSections = new HashMap<>();
        for(Map.Entry<Long, RegistrationSection> registrationSection : this.registrationSections.entrySet()){
            if(registrationSection.getValue().getUser().getMail().equals(mail)){
                registrationSections.put(registrationSection.getKey(), registrationSection.getValue());
            }
        }
        return registrationSections;
    }

    public void delete(Long id){
        registrationSectionRepository.deleteById(id);
        registrationSections.remove(id);
    }

    private void loadRegistrationSections(){
        for(RegistrationSection r: registrationSectionRepository.findAll()){
            registrationSections.put(r.getId(), r);
        }
    }

}
