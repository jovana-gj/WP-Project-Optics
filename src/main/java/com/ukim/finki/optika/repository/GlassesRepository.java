package com.ukim.finki.optika.repository;

import com.ukim.finki.optika.model.Category;
import com.ukim.finki.optika.model.Glasses;
import com.ukim.finki.optika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlassesRepository extends JpaRepository<Glasses, Long> {

    void deleteById(Long id);
}
