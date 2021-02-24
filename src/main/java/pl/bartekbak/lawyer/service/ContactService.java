package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAllContacts();

    Contact findContactById(int id);

    void saveContact(Contact contact);

    void deleteContactById(int id);
}
