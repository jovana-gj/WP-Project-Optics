package com.ukim.finki.optika.service.impl;

import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.InvalidArgumentsException;
import com.ukim.finki.optika.model.exception.InvalidUserCredentialsException;
import com.ukim.finki.optika.repository.UserRepository;
import com.ukim.finki.optika.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
