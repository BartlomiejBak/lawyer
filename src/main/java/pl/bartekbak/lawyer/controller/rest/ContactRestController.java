package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.spring.data.ContactServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    ContactServiceSpringData contactService;

    @Autowired
    public ContactRestController(ContactServiceSpringData contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactService.findAllContacts();
    }

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable int contactId) {
        Contact contact = contactService.findContactById(contactId);
        if (contact == null) {
            throw new RuntimeException("No such Id in database");
        }
        return contact;
    }

    @PostMapping("/contacts")
    public Contact addContact(@RequestBody Contact contact) {
        contact.setId(0);
        contactService.saveContact(contact);
        return contact;
    }

    @PutMapping("/contacts")
    public Contact updateContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
        return contact;
    }

    @DeleteMapping("contacts/{contactId}")
    public String deleteContact(@PathVariable int contactId) {
        Contact contact = contactService.findContactById(contactId);
        if (contact == null) {
            throw new RuntimeException("No such Id in database");
        }
        contactService.deleteContactById(contactId);
        return "Contact successfully deleted";
    }
}
