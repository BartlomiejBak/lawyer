package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.ContactRepository;
import pl.bartekbak.lawyer.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceSpringDataTest {

    @InjectMocks
    ContactServiceSpringData service;

    @Mock
    ContactRepository repository;

    @Test
    void findAllContactsTest() {
        //given
        List<Contact> list = new ArrayList<>();
        Contact contactOne = new Contact(1, "n1", "fn1","ln1");
        Contact contactTwo = new Contact(2, "n2", "fn2","ln2");
        Contact contactThree = new Contact(3, "n3", "fn3","ln3");


        list.add(contactOne);
        list.add(contactTwo);
        list.add(contactThree);

        //when
        when(repository.findAllByOrderByNameAsc()).thenReturn(list);
        List<Contact> result = service.findAllContacts();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    void findContactByIdTest() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(new Contact(1, "N", "FN", "LN")));
        Contact result = service.findContactById(1);
        //then
        assertEquals("FN", result.getFirstName());
        assertEquals("LN", result.getLastName());
    }

    @Test
    void saveContactTest() {
        //given
        Contact contact = new Contact(1, "N", "FN", "LN");
        //when
        service.saveContact(contact);
        service.saveContact(new Contact());
        //then
        verify(repository, times(1)).save(contact);
        verify(repository, times(2)).save(any());
    }

    @Test
    void deleteContactByIdTest() {
        //given
        //when
        service.deleteContactById(1);
        service.deleteContactById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}