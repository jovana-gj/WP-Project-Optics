package com.ukim.finki.optika.service.impl;


import com.ukim.finki.optika.model.Brand;
import com.ukim.finki.optika.model.exception.BrandNotFoundException;
import com.ukim.finki.optika.repository.BrandRepository;
import com.ukim.finki.optika.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> listAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> save(String name, String country) {
        return Optional.of(this.brandRepository.save(new Brand(name, country)));
    }

    @Override
    public Optional<Brand> edit(Long id, String name, String country) {
        Brand brand=this.findById(id).get();
        brand.setCountry(country);
        brand.setName(name);
        return Optional.of(this.brandRepository.save(brand));
    }

    @Override
    public void deleteById(Long id) {
        this.brandRepository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        this.brandRepository.deleteByName(name);
    }


}
