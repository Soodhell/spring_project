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

import java.util.Map;

@RestController
@Data
@AllArgsConstructor
public class SectionsController {

    private SectionsService sectionsService;
    private UserService userService;

    @GetMapping("/section/set/{id}")
    public PerformanceSections sectionSet(@PathVariable Long id) {
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
        return RepresentationSections.getPerformanceSection(sectionsService.getSections(id));
    }

    @PatchMapping("/section/api/set/{id}")
    public void sectionSet(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletRequest http) {
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
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                http.getParameter("teacher")
        );
    }

    @GetMapping("/section/get/{id}")
    public PerformanceSections sectionGet(@PathVariable Long id) {
        return RepresentationSections.getPerformanceSection(sectionsService.getSections(id));
    }

    @PutMapping("/section/api/add")
    public void apiAdd(@RequestParam("file") MultipartFile file, HttpServletRequest http){
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
                http.getParameter("title"),
                http.getParameter("content"),
                file,
                http.getParameter("teacher")
        );
    }

    @GetMapping("/sections")
    public Map<Long, PerformanceSections> getSections(){    
        return RepresentationSections.getPerformanceSections(sectionsService.getSections());
    }

}
