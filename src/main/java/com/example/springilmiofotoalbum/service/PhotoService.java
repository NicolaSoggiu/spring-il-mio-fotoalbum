package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getPhotoList(Optional<String> search) {
        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCase(search.get());
        } else {
            return photoRepository.findAll();
        }
    }
}
