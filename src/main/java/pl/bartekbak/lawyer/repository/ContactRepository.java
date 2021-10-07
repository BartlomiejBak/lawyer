package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> list();
    Optional<Contact> contactById(int id);
    void add(Contact contact);
    void update(Contact contact);
    void deleteById(int id);
}
