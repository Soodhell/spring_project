package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void add(String mail, String password, String firstName, String lastName, String roles) {
        User user = new User();
        user.setMail(mail);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void set(String mail, String password, String firstName, String lastName, String roles) {
        Optional<User> userOptional = userRepository.findByMail(mail);

        if (userOptional.isEmpty())  return;

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);

        userRepository.save(user);

    }

    public void delete(String mail) {
        userRepository.deleteUserByMail(mail);
    }

    public Optional<User> getUser(String mail) {
        return userRepository.findByMail(mail);
    }
}
