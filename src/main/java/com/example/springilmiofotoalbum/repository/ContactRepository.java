package com.example.springilmiofotoalbum.repository;

import com.example.springilmiofotoalbum.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
