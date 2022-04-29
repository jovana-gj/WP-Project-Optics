package com.ukim.finki.optika.service;

import com.ukim.finki.optika.model.User;

public interface AuthService {
    User login(String username, String password);
}
