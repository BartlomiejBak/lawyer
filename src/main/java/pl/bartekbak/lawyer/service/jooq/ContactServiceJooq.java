package pl.bartekbak.lawyer.service.jooq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.repository.ContactRepository;
import pl.bartekbak.lawyer.service.ContactService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactServiceJooq implements ContactService {

    ContactRepository contactRepository;

    @Autowired
    public ContactServiceJooq(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> findAllContacts() {
        return contactRepository.list()
                .stream()
                .map(Contact::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDTO findContactById(UUID id) {
        Optional<Contact> result = contactRepository.contactById(id);
        ContactDTO contact;
        contact = result.map(Contact::toDto).orElse(null);
        return contact;
    }

    @Override
    public void saveContact(ContactDTO contact) {
        contactRepository.add(Contact.fromDto(contact));
    }

    @Override
    public void deleteContactById(UUID id) {
        contactRepository.deleteById(id);
    }
}
