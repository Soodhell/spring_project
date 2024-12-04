package com.example.demo.classDTO.news;

import lombok.Data;

@Data
public class NewsAddDTO {
    private String title;
    private String content;
    private int type_news;
}
