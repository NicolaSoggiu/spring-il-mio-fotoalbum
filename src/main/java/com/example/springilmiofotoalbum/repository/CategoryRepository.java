package com.example.springilmiofotoalbum.repository;

import com.example.springilmiofotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
