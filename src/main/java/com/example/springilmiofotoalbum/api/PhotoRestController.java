package com.example.springilmiofotoalbum.api;

import com.example.springilmiofotoalbum.exception.PhotoNameUniqueException;
import com.example.springilmiofotoalbum.exception.PhotoNotFoundException;
import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photos")
@CrossOrigin
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam Optional<String> search) throws RuntimeException {
        try {
            return photoService.getPhotoList();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Photo show(@PathVariable Integer id){
        try{
            return photoService.getPhotoById(id);
        }catch (PhotoNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Photo create(@Valid @RequestBody Photo book) {
        try {
            return photoService.createPhoto(book);
        } catch (PhotoNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        photo.setId(id);
        try {
            return photoService.editPhoto(photo);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Photo photoToDelete = photoService.getPhotoById(id);
            photoService.deletePhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page")
    public Page<Photo> pagedIndex(
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {

        return photoService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/page/v2")
    public Page<Photo> pagedIndexV2(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return photoService.getPage(pageable);
    }
}
