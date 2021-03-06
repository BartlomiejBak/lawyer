package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.ContactRepository;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.service.ContactService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceSpringData implements ContactService {

    ContactRepository contactRepository;

    @Autowired
    public ContactServiceSpringData(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> findAllContacts() {
        return contactRepository.findAllByOrderByNameAsc()
                .stream()
                .map(Contact::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findContactById(int id) {
        Optional<Contact> result = contactRepository.findById(id);
        ContactDTO contact;
        if (result.isPresent()) {
            contact = result.get().toDto();
        } else {
            throw new RuntimeException("Id not found");
        }
        return contact;
    }

    @Override
    public void saveContact(ContactDTO contact) {
        contactRepository.save(Contact.fromDto(contact));
    }

    @Override
    public void deleteContactById(int id) {
        contactRepository.deleteById(id);
    }
}
