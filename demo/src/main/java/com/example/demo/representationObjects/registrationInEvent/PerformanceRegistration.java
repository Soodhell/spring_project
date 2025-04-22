package com.example.demo.representationObjects.registrationInEvent;

import lombok.Data;

@Data
public class PerformanceRegistration {
    private Long id;
    private Long news_id;
    private String mail;
    private String title;
    private String content;

    PerformanceRegistration(Long id, Long news_id,String mail, String title, String content) {
        this.id = id;
        this.news_id = news_id;
        this.mail = mail;
        this.title = title;
        this.content = content;
    }
}
