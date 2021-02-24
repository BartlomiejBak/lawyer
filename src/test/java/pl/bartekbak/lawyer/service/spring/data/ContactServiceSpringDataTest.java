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

    Contact contactOne = Contact.builder()
            .contactId(1)
            .name("n1")
            .firstName("fn1")
            .lastName("ln1")
            .build();

    @Test
    void findAllContactsTest() {
        //given
        List<Contact> list = new ArrayList<>();
        Contact contactTwo = Contact.builder()
                .contactId(2)
                .name("n2")
                .firstName("fn2")
                .lastName("ln2")
                .build();
        Contact contactThree = Contact.builder()
                .contactId(3)
                .name("n3")
                .firstName("fn3")
                .lastName("ln3")
                .build();


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
                .thenReturn(Optional.of(contactOne));
        Contact result = service.findContactById(1);
        //then
        assertEquals("fn1", result.getFirstName());
        assertEquals("ln1", result.getLastName());
    }

    @Test
    void saveContactTest() {
        //given
        //when
        service.saveContact(contactOne);
        service.saveContact(new Contact());
        //then
        verify(repository, times(1)).save(contactOne);
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
