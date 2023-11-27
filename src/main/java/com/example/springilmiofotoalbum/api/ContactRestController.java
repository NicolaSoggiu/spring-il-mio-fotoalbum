package com.example.springilmiofotoalbum.api;

import com.example.springilmiofotoalbum.model.Contact;
import com.example.springilmiofotoalbum.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
public class ContactRestController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public List<Contact> index() {
        return contactService.getMessageList();
    }

    @PostMapping
    public Contact create(@Valid @RequestBody Contact contact) {
        return contactService.create(contact);
    }
}
