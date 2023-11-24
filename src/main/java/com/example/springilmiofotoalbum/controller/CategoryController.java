package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.exception.CategoryNameUniqueException;
import com.example.springilmiofotoalbum.exception.CategoryNotFoundException;
import com.example.springilmiofotoalbum.model.Category;
import com.example.springilmiofotoalbum.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("categoryObj", new Category());
        return "categories/index";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("categoryObj") Category formCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryService.getAll());
            return "categories/index";
        }
        try {
            categoryService.save(formCategory);
            return "redirect:/categories";
        } catch (CategoryNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A category with name " + e.getMessage() + " already exists");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Category categoryToDelete = categoryService.getCategory(id);
            categoryService.deleteCategory(categoryToDelete);
            redirectAttributes.addFlashAttribute("message", "Category delete!");
            return "redirect:/categories";
        } catch (CategoryNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
