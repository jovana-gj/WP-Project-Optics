package com.ukim.finki.optika.web;


import com.ukim.finki.optika.model.Order;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;
import com.ukim.finki.optika.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String getOrderPage(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        List<Order> orders = this.orderService.findAllByUser(user);
        model.addAttribute("glasses", orders);
        model.addAttribute("bodyContent", "my-orders");
        return "main_template";
    }

    @PostMapping("/add")
    public String addOrder(@RequestParam Long id, @RequestParam int quantity, Authentication authentication) throws GlassesNotFoundException {

        User user = (User) authentication.getPrincipal();
        this.orderService.addOrder(id, quantity, user);
        return "redirect:/orders";
    }


}
