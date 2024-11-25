package com.example.demo.users.services;

import com.example.demo.users.models.ModifacationUserDetails;
import com.example.demo.users.models.User;
import com.example.demo.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModifacationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByMail(mail);
        return user.map(ModifacationUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(mail + " not found"));
    }
}
