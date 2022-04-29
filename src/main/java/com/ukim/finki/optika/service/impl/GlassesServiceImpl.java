package com.ukim.finki.optika.service.impl;


import com.ukim.finki.optika.model.*;
import com.ukim.finki.optika.model.exception.BrandNotFoundException;
import com.ukim.finki.optika.model.exception.CategoryNotFoundException;
import com.ukim.finki.optika.model.exception.GlassesNotFoundException;
import com.ukim.finki.optika.repository.BrandRepository;
import com.ukim.finki.optika.repository.GlassesRepository;
import com.ukim.finki.optika.repository.CartItemRepository;
import com.ukim.finki.optika.service.GlassesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GlassesServiceImpl implements GlassesService {

    private final GlassesRepository glassesRepository;
    private final BrandRepository brandRepository;
    private final CartItemRepository cartItemRepositoryRepository;

    public GlassesServiceImpl(GlassesRepository glassesRepository, BrandRepository brandRepository, CartItemRepository cartItemRepositoryRepository) {
        this.glassesRepository = glassesRepository;
        this.brandRepository = brandRepository;
        this.cartItemRepositoryRepository = cartItemRepositoryRepository;
    }

    @Override
    public List<Glasses> listAll() {
        return this.glassesRepository.findAll();
    }

    @Override
    public Optional<Glasses> findById(Long id) {
        return this.glassesRepository.findById(id);
    }

    @Override
    public Optional<Glasses> save(String imgUrl, Integer price, Integer quantity, String category, Long brand) throws BrandNotFoundException, CategoryNotFoundException {
        Brand b=this.brandRepository.findById(brand).orElseThrow(BrandNotFoundException::new);
        Category cat= Category.findByText(category);
        //this.glassesRepository.deleteByName(name);
        return Optional.of(this.glassesRepository.save(new Glasses(imgUrl, price, quantity, cat, b)));
    }

    @Override
    public Optional<Glasses> edit(Long id, String imgUrl, Integer price, Integer quantity, String category, Long brand) throws BrandNotFoundException, GlassesNotFoundException {
        Glasses glasses=this.glassesRepository.findById(id).orElseThrow(GlassesNotFoundException::new);
        glasses.setImgUrl(imgUrl);
        glasses.setPrice(price);
        glasses.setQuantity(quantity);

        Brand b=this.brandRepository.findById(brand).orElseThrow(BrandNotFoundException::new);
        glasses.setBrand(b);
        Category cat=Category.findByText(category);
        glasses.setCategory(cat);

        return Optional.of(this.glassesRepository.save(glasses));
    }

    @Override
    public void deleteById(Long id) {
        this.glassesRepository.deleteById(id);
    }



    @Override
    public Glasses get(Long id) {
        return this.glassesRepository.findById(id).get();
    }

    @Override
    public Optional<Glasses> editQuantity(Long id, Integer quantity) throws GlassesNotFoundException {
        Glasses glasses=this.glassesRepository.findById(id).orElseThrow(GlassesNotFoundException::new);
        glasses.setQuantity(quantity);
        return Optional.of(this.glassesRepository.save(glasses));
    }
}
