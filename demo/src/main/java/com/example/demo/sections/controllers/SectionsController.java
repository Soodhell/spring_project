package com.example.demo.sections.controllers;

import com.example.demo.representationObjects.sections.PerformanceSections;
import com.example.demo.representationObjects.sections.RepresentationSections;
import com.example.demo.sections.services.SectionsService;
import com.example.demo.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Data
@AllArgsConstructor
@CrossOrigin
public class SectionsController {

    private SectionsService sectionsService;
    private UserService userService;

    @PatchMapping("/section/set/{id}")
    public void sectionSet(@PathVariable Long id,
                           @RequestParam("file") MultipartFile file,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("teacher") String teacher) {
        if(!userService.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get()
                .getRoles()
                .equals("ADMIN")
        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        sectionsService.setSections(
                id,
                title,
                content,
                file,
                teacher
        );
    }

    @GetMapping("/sections/get/{id}")
    public PerformanceSections sectionGet(@PathVariable Long id) {
        return RepresentationSections.getPerformanceSection(sectionsService.getSections(id));
    }

    @PutMapping("/section/add")
    public void apiAdd(@RequestParam("file") MultipartFile file,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content,
                       @RequestParam("teacher") String teacher){
        if(!userService.getUser(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                )
                .get()
                .getRoles()
                .equals("ADMIN")
        ) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        sectionsService.save(
                title,
                content,
                file,
                teacher
        );
    }

    @GetMapping("/sections")
    public List<PerformanceSections> getSections(){
        return RepresentationSections.getPerformanceSections(sectionsService.getSections());
    }

}
