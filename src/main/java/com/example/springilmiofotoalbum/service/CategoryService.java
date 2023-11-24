package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.exception.CategoryNameUniqueException;
import com.example.springilmiofotoalbum.exception.CategoryNotFoundException;
import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findByOrderByName();
    }

    public Category save(Category category) throws CategoryNameUniqueException {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryNameUniqueException(category.getName());
        }
        category.setName(category.getName().toLowerCase());
        return categoryRepository.save(category);
    }

    public Category getCategory(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id " + id + " not found!"));
    }

    public void deleteCategory(Integer id) {
        Category categoryDeleted = getCategory(id);
        List<Photo> photos = categoryDeleted.getPhotos();
        if (photos.size() > 0) {
            for (Photo photo : photos ) {
                List<Category> categories = photo.getCategories();
                categories.remove(categoryDeleted);
            }
            categoryDeleted.setPhotos(new ArrayList<>());
        }
        categoryRepository.deleteById(id);
    }
}
