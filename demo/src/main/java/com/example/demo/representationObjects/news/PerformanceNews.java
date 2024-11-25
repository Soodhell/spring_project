package com.example.demo.representationObjects.news;

import lombok.Data;

@Data
public class PerformanceNews {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String nameImg;

    public PerformanceNews(Long id, String title, String content, String author, String nameImg) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.nameImg = nameImg;
    }
}
