package com.example.springilmiofotoalbum.controller;

import com.example.springilmiofotoalbum.exception.MessageNotFoundException;
import com.example.springilmiofotoalbum.model.Contact;
import com.example.springilmiofotoalbum.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        model.addAttribute("contactList", contactService.getMessageList());
        return "/contacts/list";
    }

    @GetMapping("/show/{id}")
    public String show (@PathVariable Integer id, Model model){
        try{
            Contact message = contactService.getMessageById(id);
            model.addAttribute("message", message);
            return "messages/show";
        }catch (MessageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try{
            Contact messageToDelete = contactService.getMessageById(id);
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute(
                    "message",
                    "Message deleted!"
            );
            return "redirect:/messages";
        }catch (MessageNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
