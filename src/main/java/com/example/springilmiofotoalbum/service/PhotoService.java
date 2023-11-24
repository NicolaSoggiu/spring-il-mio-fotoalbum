package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.exception.PhotoNameUniqueException;
import com.example.springilmiofotoalbum.exception.PhotoNotFoundException;
import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getPhotoList(Optional<String> search) {
        if (search.isPresent()) {
            return photoRepository.findByTitleContainingIgnoreCase(search.get());
        } else {
            return photoRepository.findAll();
        }
    }

    public Photo getPhotoById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException("Photo with id " + id + " not found!");
        }
    }

    public Photo createPhoto(Photo photo) throws PhotoNameUniqueException {
        try {
            return photoRepository.save(photo);
        } catch (RuntimeException e) {
            throw new PhotoNameUniqueException("Photo with this name already exist!");
        }
    }

    public Photo editPhoto(Photo photo) throws PhotoNotFoundException {
        Photo photoToEdit = getPhotoById(photo.getId());
        photoToEdit.setTitle(photo.getTitle());
        photoToEdit.setVisible(photo.getVisible());
        photoToEdit.setDescription(photo.getDescription());
        photoToEdit.setUrl(photo.getUrl());
        photoToEdit.setCategories(photo.getCategories());
        return photoRepository.save(photoToEdit);
    }

    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }

}
