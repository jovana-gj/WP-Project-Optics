package com.ukim.finki.optika.service;

import com.ukim.finki.optika.model.CartItem;
import com.ukim.finki.optika.model.Glasses;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<CartItem> findAllByUser(User user);
    Optional<CartItem> addItemToCart(Long glassesId, User user) throws GlassesNotFoundException;
    void removeItem(Long id);
}
