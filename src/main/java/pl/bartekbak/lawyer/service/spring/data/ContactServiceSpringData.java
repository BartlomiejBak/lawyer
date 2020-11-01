package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.ContactRepository;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import java.util.List;

@Service
public class ContactServiceSpringData implements ContactService {
    ContactRepository contactRepository;

    @Autowired
    public ContactServiceSpringData(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAllContacts() {
        return null;
    }
}
