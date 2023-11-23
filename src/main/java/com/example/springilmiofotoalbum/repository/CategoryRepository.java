package com.example.springilmiofotoalbum.repository;

import com.example.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByOrderByName();

    boolean existsByName(String name);
}
