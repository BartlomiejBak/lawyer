package pl.bartekbak.lawyer.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listAllContacts(Model model) {
        List<Contact> contactList = service.findAllContacts();
        model.addAttribute("contacts", contactList);
        return "contacts/list-contacts";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "contacts/add-contact-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("contactId") int id, Model model) {
        Contact contact = service.findContactById(id);
        model.addAttribute(contact);
        return "contacts/add-contact-form";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        service.saveContact(contact);

        //redirect prevents multiple saving
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("contactId") int id) {
        service.deleteContactById(id);
        return "redirect:list";
    }

}
