package pl.bartekbak.lawyer.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.exceptions.ResourceNotFoundException;
import pl.bartekbak.lawyer.service.spring.data.ContactServiceSpringData;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {

    ContactServiceSpringData contactService;

    @Autowired
    public ContactRestController(ContactServiceSpringData contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<ContactDTO> getAllContacts() {
        return contactService.findAllContacts();
    }

    @GetMapping("/{contactId}")
    public ContactDTO getContact(@PathVariable int contactId) {
        ContactDTO contact = contactService.findContactById(contactId);
        if (contact == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        return contact;
    }

    @PostMapping
    public ContactDTO addContact(@RequestBody ContactDTO contact) {
        contact.setContactId(0);
        contactService.saveContact(contact);
        return contact;
    }

    @PutMapping
    public ContactDTO updateContact(@RequestBody ContactDTO contact) {
        contactService.saveContact(contact);
        return contact;
    }

    @DeleteMapping("/contactId}")
    public String deleteContact(@PathVariable int contactId) {
        ContactDTO contact = contactService.findContactById(contactId);
        if (contact == null) {
            throw new ResourceNotFoundException("No such Id in database");
        }
        contactService.deleteContactById(contactId);
        return "Contact successfully deleted";
    }
}
