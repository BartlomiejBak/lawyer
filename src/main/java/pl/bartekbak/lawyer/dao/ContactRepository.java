package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Contact;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    public List<Contact> findAllByOrderByNameAsc();
}
