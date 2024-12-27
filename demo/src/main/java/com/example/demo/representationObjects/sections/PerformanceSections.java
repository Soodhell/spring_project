package com.example.demo.representationObjects.sections;

import lombok.Data;

@Data
public class PerformanceSections {
    private Long id;
    private String title;
    private String content;
    private String nameImg;
    private String teacher;

    public PerformanceSections(Long id, String title, String content, String nameImg, String teacher) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nameImg = nameImg;
        this.teacher = teacher;
    }
}
