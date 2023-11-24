package com.example.springilmiofotoalbum.api;

import com.example.springilmiofotoalbum.model.Photo;
import com.example.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/photos")
@CrossOrigin
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> index(@RequestParam Optional<String> search) {
        return photoService.getPhotoList(search);
    }
}
