package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.ContactRepository;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceSpringData implements ContactService {
    ContactRepository contactRepository;

    @Autowired
    public ContactServiceSpringData(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Contact findContactById(int id) {
        Optional<Contact> result = contactRepository.findById(id);
        Contact contact = null;
        if (result.isPresent()) {
            contact = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return contact;
    }

    @Override
    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(int id) {
        contactRepository.deleteById(id);
    }
}
