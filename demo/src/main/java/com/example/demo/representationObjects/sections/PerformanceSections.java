package com.example.demo.representationObjects.sections;

import lombok.Data;

@Data
public class PerformanceSections {
    private String title;
    private String content;
    private String nameImg;
    private String teacher;

    public PerformanceSections(String title, String content, String nameImg, String teacher) {
        this.title = title;
        this.content = content;
        this.nameImg = nameImg;
        this.teacher = teacher;
    }
}
