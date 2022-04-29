package com.ukim.finki.optika.repository;

import com.ukim.finki.optika.model.Order;
import com.ukim.finki.optika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
}
