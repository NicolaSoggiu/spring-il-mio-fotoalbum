package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.exception.CategoryNameUniqueException;
import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findByOrderByName();
    }

    public Category save(Category category) throws CategoryNameUniqueException {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryNameUniqueException(category.getName());
        }
        category.setName(category.getName());
        return categoryRepository.save(category);
    }
}
