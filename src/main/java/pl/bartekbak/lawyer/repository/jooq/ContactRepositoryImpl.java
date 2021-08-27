package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Contact;
import pl.bartekbak.lawyer.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    @Override
    public List<Contact> list() {
        return List.of();
    }

    @Override
    public Optional<Contact> contactById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Contact contact) {

    }

    @Override
    public void deleteById(int id) {

    }
}
