package com.Singlestone.ContactApiENTRY.apitesting.controller;

import com.Singlestone.ContactApiENTRY.Controller.ContactsController;
import com.Singlestone.ContactApiENTRY.Model.Contact;
import com.Singlestone.ContactApiENTRY.util.ContactUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext( classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private ContactsController contactsController;
    @Test
    public void createContact() {
        Contact contact = ContactUtil.createContact_WithNoId();
        Contact c = contactsController.createContact(contact);

        assertEquals(contact.getName().getFirstName(), c.getName().getFirstName());
        assertEquals(contact.getEmail(), c.getEmail());
        assertEquals(contact.getAddress().getCity(), c.getAddress().getCity());
        assertEquals(contact.getPhone().get(0).getNumber(), c.getPhone().get(0).getNumber());

    }

    @Test
    public void createContactWithId() {
        Contact contact = ContactUtil.createContact_WithNoId();
        contact.setId(0L);
        try {
            contactsController.createContact(contact);
        }
        catch(ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void UpdateExistingContact() {
        Contact contact = ContactUtil.createContact_WithNoId();
        Contact createResp = contactsController.createContact(contact);

        Contact updateContact = ContactUtil.createContact_WithNoId();
        updateContact.getName().setFirstName("First Name Updated !");
        Contact c = contactsController.updateContact(createResp.getId(), updateContact);

        assertNotEquals(contact.getName().getFirstName(), c.getName().getFirstName());
    }

    @Test
    public void updateNewContactWithNoID() {
        Contact contact = ContactUtil.createContact_WithNoId();
        try{
            contactsController.updateContact(null, contact);
        }
        catch(ResponseStatusException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    @Test
    public void getAllContacts() {
        Contact contact1 = ContactUtil.createContact_WithNoId();
        Contact contact2 = ContactUtil.createContact_WithNoId();
        contactsController.createContact(contact1);
        contactsController.createContact(contact2);

        List<Contact> contacts = contactsController.getAllContacts();
        assertEquals(2, contacts.size());
    }

    @Test
    public void getContactWithId() {
        Contact contact = ContactUtil.createContact_WithNoId();
        Contact c = contactsController.createContact(contact);

        contactsController.getContact(c.getId());
    }

    @Test
    public void getContact_WithNonExistentId() {
        try{
            Contact c = contactsController.getContact(0L);
        }
        catch(ResponseStatusException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }

    @Test
    public void deleteContactWithId() {
        Contact contact = ContactUtil.createContact_WithNoId();
        Contact c = contactsController.createContact(contact);

        contactsController.deleteContact(c.getId());
    }

    @Test
    public void deleteContact_WithNonExistentId() {
        try{
            contactsController.deleteContact(0L);
        }
        catch (ResponseStatusException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }
}
