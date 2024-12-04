package com.example.demo.representationObjects.news;

import lombok.Data;

@Data
public class PerformanceNews {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String nameImg;
    private int type_news;

    public PerformanceNews(Long id, String title, String content, String author, String nameImg, int type_news) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.nameImg = nameImg;
        this.type_news = type_news;
    }
}
