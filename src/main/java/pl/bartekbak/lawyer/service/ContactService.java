package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;

public interface ContactService {
    public List<Contact> findAllContacts();

    public Contact findContactById(int id);

    public void saveContact(Contact contact);

    public void deleteContactById(int id);
}
