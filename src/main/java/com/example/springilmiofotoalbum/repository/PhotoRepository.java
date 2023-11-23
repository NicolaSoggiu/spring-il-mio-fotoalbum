package com.example.springilmiofotoalbum.repository;

import com.example.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByTitleContainingIgnoreCase(String titleKeyWord);
}
