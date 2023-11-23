package com.example.springilmiofotoalbum.exception;

public class PhotoNotFoundException extends RuntimeException{
    public PhotoNotFoundException(String message) {
        super(message);
    }
}
