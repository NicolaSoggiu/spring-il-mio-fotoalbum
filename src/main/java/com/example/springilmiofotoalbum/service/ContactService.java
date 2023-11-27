package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.exception.MessageNotFoundException;
import com.example.springilmiofotoalbum.model.Contact;
import com.example.springilmiofotoalbum.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getMessageList() {
        return contactRepository.findAll();
    }

    public Contact getMessageById(Integer id) throws MessageNotFoundException {
        Optional<Contact> result = contactRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + id + " not found!");
        }
    }

    public Contact create(Contact contact) {
        contact.setId(null);
        contact.setSendAt(LocalDate.now());
        return contactRepository.save(contact);
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }
}
