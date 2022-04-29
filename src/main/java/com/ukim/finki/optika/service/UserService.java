package com.ukim.finki.optika.service;

import com.ukim.finki.optika.model.Role;
import com.ukim.finki.optika.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
