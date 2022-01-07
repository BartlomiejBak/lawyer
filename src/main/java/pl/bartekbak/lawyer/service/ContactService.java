package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.ContactDTO;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    List<ContactDTO> findAllContacts();

    ContactDTO findContactById(UUID id);

    void saveContact(ContactDTO contact);

    void deleteContactById(UUID id);
}
