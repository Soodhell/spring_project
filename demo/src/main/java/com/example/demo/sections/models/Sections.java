package com.example.demo.sections.models;

import com.example.demo.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sections")
@Data
public class Sections {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "img_name")
    private String nameImg;
    @Column(name = "img_path")
    private String pathImg;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private User teacher;
}
