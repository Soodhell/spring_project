package com.example.demo.users.repositories;

import com.example.demo.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByMail(String Mail);
}
