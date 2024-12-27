package com.example.demo.news.models;

import com.example.demo.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "news")
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "path_img")
    private String pathImg;
    @Column(name = "name_img")
    private String nameImg;
    @Column(name = "type_news")
    private int type_news;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private User author;
}
