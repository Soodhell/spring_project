package com.example.demo.representationObjects.news;

import lombok.Data;

@Data
public class PerformanceNews {
    private String title;
    private String content;
    private String author;
    private String nameImg;

    public PerformanceNews(String title, String content, String author, String nameImg) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.nameImg = nameImg;
    }
}
