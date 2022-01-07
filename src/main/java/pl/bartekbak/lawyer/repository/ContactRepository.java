package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {

    List<Contact> list();
    Optional<Contact> contactById(UUID id);
    void add(Contact contact);
    void update(Contact contact);
    void deleteById(UUID id);
}
