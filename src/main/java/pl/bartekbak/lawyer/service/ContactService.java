package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> findAllContacts();

    ContactDTO findContactById(int id);

    void saveContact(ContactDTO contact);

    void deleteContactById(int id);
}
