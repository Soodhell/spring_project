package com.example.demo.registrationInEvent.models;

import com.example.demo.news.models.News;
import com.example.demo.users.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "registration_in_event")
@Data
public class RegistrationInEventModel {

    public RegistrationInEventModel(){
    }

    public RegistrationInEventModel(User user, News news){
        this.user = user;
        this.event = news;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private News event;
}
