package pl.bartekbak.lawyer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    public List<Contact> findAllByOrderByNameAsc();
}
