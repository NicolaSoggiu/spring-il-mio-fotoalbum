package com.example.springilmiofotoalbum.exception;

public class PhotoNameUniqueException extends RuntimeException{
    public PhotoNameUniqueException(String message) {
        super(message);
    }
}
