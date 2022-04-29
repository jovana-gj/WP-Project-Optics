package com.ukim.finki.optika.service.impl;

import com.ukim.finki.optika.model.Role;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.InvalidArgumentsException;
import com.ukim.finki.optika.model.exception.PasswordsDoNotMatchException;
import com.ukim.finki.optika.model.exception.UsernameAlreadyExistsException;
import com.ukim.finki.optika.repository.UserRepository;
import com.ukim.finki.optika.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException();
        }
        User user=new User(username, passwordEncoder.encode(password), name, surname, role);
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
}
