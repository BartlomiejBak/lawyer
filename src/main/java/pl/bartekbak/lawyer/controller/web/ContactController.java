package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
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
    public String saveContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "contacts/add-contact-form";
        }
        service.saveContact(contact);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("contactId") int id) {
        service.deleteContactById(id);
        return "redirect:list";
    }

}
