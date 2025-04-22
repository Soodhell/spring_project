package com.example.demo.classDTO.news;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewsAddDTO{
    private String title;
    private String content;
    private String type_news;
}
