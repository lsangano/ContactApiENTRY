package com.Singlestone.ContactApiENTRY.Controller;


import com.Singlestone.ContactApiENTRY.Dao.ContactDao;
import com.Singlestone.ContactApiENTRY.Model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactsController {
    @Autowired
    private ContactDao contactDao;

    @GetMapping("contacts")
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contactDao.findAll().forEach(contacts::add);
        return contacts;
    }

    @PostMapping("contacts")
    public Contact createContact(@RequestBody Contact contact) {
        if(contact.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contact cannot be created with pre-populated ID.");
        Contact resp = contactDao.save(contact);
        return resp;
    }

    @GetMapping("contacts/{id}")
    public Contact getContact(@PathVariable Long id) {
        Optional<Contact> searchForContact = contactDao.findById(id);
        if(! searchForContact.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provided Contact ID not found.");
        return searchForContact.get();
    }
}
