package com.ukim.finki.optika.repository;

import com.ukim.finki.optika.model.CartItem;
import com.ukim.finki.optika.model.Glasses;
import com.ukim.finki.optika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByUser(User user);
    CartItem findByGlassesAndUser(Glasses glasses, User user);
}
