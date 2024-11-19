package com.example.demo.service.users;

import com.example.demo.model.users.User;
import com.example.demo.repository.users.UserRepository;
import lombok.AllArgsConstructor;
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
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);

        userRepository.save(user);

    }

    public void delete(String mail) {
        Optional<User> user = userRepository.findByMail(mail);
        if (user.isEmpty())  return;
        userRepository.delete(user.get());
    }

    public Optional<User> getUser(String mail) {
        return userRepository.findByMail(mail);
    }
}
