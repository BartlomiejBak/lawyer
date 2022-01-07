package pl.bartekbak.lawyer.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.service.ContactService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    private static final String CONTACT_ADD_FORM = "contacts/add-contact-form";

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/list")
    public String listAllContacts(Model model) {
        List<ContactDTO> contactList = contactService.findAllContacts();
        model.addAttribute("contacts", contactList);
        return "contacts/list-contacts";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        ContactDTO contact = new ContactDTO();
        model.addAttribute("contact", contact);
        return CONTACT_ADD_FORM;
    }

    @GetMapping("/{contactId}/edit")
    public String showFormForUpdate(@PathVariable UUID contactId, Model model) {
        ContactDTO contact = contactService.findContactById(contactId);
        model.addAttribute("contact", contact);
        return CONTACT_ADD_FORM;
    }

    @PostMapping("/save")
    public String saveContact(@Valid @ModelAttribute("contact") ContactDTO contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                log.debug(objectError.toString()));
            return CONTACT_ADD_FORM;
        }
        contactService.saveContact(contact);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("contactId") UUID id) {
        contactService.deleteContactById(id);
        return "redirect:list";
    }

    @GetMapping("/{contactId}")
    public ModelAndView showContact(@PathVariable UUID contactId) {
        ModelAndView mav = new ModelAndView("contacts/contact-details");
        mav.addObject(contactService.findContactById(contactId));
        return mav;
    }

}
