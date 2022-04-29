package com.ukim.finki.optika.service.impl;


import com.ukim.finki.optika.model.CartItem;
import com.ukim.finki.optika.model.Glasses;
import com.ukim.finki.optika.model.Order;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;
import com.ukim.finki.optika.repository.CartItemRepository;
import com.ukim.finki.optika.repository.OrderRepository;
import com.ukim.finki.optika.service.GlassesService;
import com.ukim.finki.optika.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final GlassesService glassesService;

    public OrderServiceImpl(OrderRepository orderRepository, CartItemRepository cartItemRepository, GlassesService glassesService) {
        this.orderRepository = orderRepository;

        this.cartItemRepository = cartItemRepository;
        this.glassesService = glassesService;
    }
    @Override
    public List<Order> findAllByUser(User user) {
        return this.orderRepository.findAllByUser(user);
    }

    @Override
    public Optional<Order> addOrder(Long serialNumber, int quantity, User user) throws GlassesNotFoundException {
        Glasses glasses = this.glassesService.findById(serialNumber).orElseThrow(GlassesNotFoundException::new);
        Order order=null;
        if(this.findAllByUser(user).stream().anyMatch(o -> o.getSerialNumber().equals(serialNumber))){
            order = this.findAllByUser(user).stream().filter(o -> o.getSerialNumber().equals(serialNumber)).findFirst().get();
            order.setQuantity(order.getQuantity()+quantity);
        }
        else {
            order = new Order(serialNumber, quantity, glasses.getImgUrl(), glasses.getPrice(), user, glasses.getBrand());
        }
        int glassesQuantity = glasses.getQuantity();
        int left=glassesQuantity-quantity;
        if(left==0){
            glassesService.deleteById(serialNumber);
        }
        else{
            glassesService.editQuantity(serialNumber, left);
        }
        return Optional.of(this.orderRepository.save(order));
    }
}
