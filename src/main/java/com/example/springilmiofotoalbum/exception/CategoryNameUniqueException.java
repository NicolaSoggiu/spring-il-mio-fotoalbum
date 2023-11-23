package com.example.springilmiofotoalbum.exception;

public class CategoryNameUniqueException extends RuntimeException{
    public CategoryNameUniqueException(String message) {
        super(message);
    }
}
