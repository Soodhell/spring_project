package com.example.demo.registration.models;

import com.example.demo.sections.models.Sections;
import com.example.demo.users.models.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "registration_section")
@Data
public class RegistrationSection {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Sections section;
}
