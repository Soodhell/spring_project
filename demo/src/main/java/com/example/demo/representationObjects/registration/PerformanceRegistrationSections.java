package com.example.demo.representationObjects.registration;

import lombok.Data;

@Data
public class PerformanceRegistrationSections {
    private String title;
    private String nameImg;

    public PerformanceRegistrationSections(String title, String nameImg) {
        this.title = title;
        this.nameImg = nameImg;
    }
}
