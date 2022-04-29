package com.ukim.finki.optika.service;

import com.ukim.finki.optika.model.Brand;
import com.ukim.finki.optika.model.Category;
import com.ukim.finki.optika.model.User;
import com.ukim.finki.optika.model.exception.BrandNotFoundException;
import com.ukim.finki.optika.model.exception.CategoryNotFoundException;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;
import com.ukim.finki.optika.model.Glasses;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GlassesService {
    List<Glasses> listAll();

    Optional<Glasses> findById(Long id);

    Optional<Glasses> save(String imgUrl, Integer price, Integer quantity, String category, Long brand) throws BrandNotFoundException, CategoryNotFoundException;

    Optional<Glasses> edit(Long id, String imgUrl, Integer price, Integer quantity, String category, Long brand) throws BrandNotFoundException, GlassesNotFoundException;

    void deleteById(Long id);

    Glasses get(Long id);

    Optional<Glasses> editQuantity(Long id, Integer quantity) throws GlassesNotFoundException;

}
