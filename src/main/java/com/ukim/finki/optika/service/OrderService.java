package com.ukim.finki.optika.service;

import com.ukim.finki.optika.model.Brand;
import com.ukim.finki.optika.model.Order;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAllByUser(User user);
    Optional<Order> addOrder(Long serialNumber, int quantity, User user) throws GlassesNotFoundException;
}
