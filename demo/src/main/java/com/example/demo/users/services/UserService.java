package com.example.demo.users.services;

import com.example.demo.users.models.User;
import com.example.demo.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private UserRepository userRepository;

    private final HashMap<String, User> users = new HashMap<>();

    public Map<String, User> findAll() {
        if (users.isEmpty()) loadUsers();
        return users;
    }

    public void add(String mail, String password, String firstName, String lastName, String roles) {
        if (users.isEmpty()) loadUsers();

        User user = new User();
        user.setMail(mail);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);
        userRepository.save(user);
        users.put(mail, user);
    }

    public void set(String mail, String password, String firstName, String lastName, String roles) {
        if (users.isEmpty())loadUsers();

        Optional<User> userOptional = userRepository.findByMail(mail);
        if (userOptional.isEmpty())  return;

        User user = userOptional.get();

        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(roles);

        userRepository.save(user);
        users.put(mail, user);

    }

    public void delete(String mail) {
        if (users.isEmpty()) loadUsers();

        Optional<User> user = userRepository.findByMail(mail);
        if (user.isEmpty())  return;
        userRepository.delete(user.get());
        users.remove(mail);
    }

    public Optional<User> getUser(String mail) {
        if (users.isEmpty()) loadUsers();
        return Optional.ofNullable(users.get(mail));
    }

    private void loadUsers(){
        for(User user : userRepository.findAll()){
            users.put(user.getMail(), user);
        }
    }
}
