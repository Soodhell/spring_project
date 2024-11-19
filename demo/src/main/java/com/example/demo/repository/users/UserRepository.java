package com.example.demo.repository.users;

import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByMail(String Mail);
}
