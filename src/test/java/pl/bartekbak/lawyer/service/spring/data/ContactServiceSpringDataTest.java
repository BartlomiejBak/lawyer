package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.ContactRepository;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.dto.ContactDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.entity.Contact;

import java.time.LocalDateTime;
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
            .address(Address.builder().build())
            .correspondenceAddress(Address.builder().build())
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
                .address(Address.builder().build())
                .correspondenceAddress(Address.builder().build())
                .build();
        Contact contactThree = Contact.builder()
                .contactId(3)
                .name("n3")
                .firstName("fn3")
                .lastName("ln3")
                .address(Address.builder().build())
                .correspondenceAddress(Address.builder().build())
                .build();

        list.add(contactTwo);
        list.add(contactThree);

        //when
        when(repository.findAllByOrderByNameAsc()).thenReturn(list);
        List<ContactDTO> result = service.findAllContacts();

        //then
        assertEquals(2, result.size());
        verify(repository, times(1)).findAllByOrderByNameAsc();
    }

    @Test
    void findContactByIdTest() {
        //given
        //when
        when(repository.findById(1))
                .thenReturn(Optional.of(contactOne));
        ContactDTO result = service.findContactById(1);
        //then
        assertEquals("fn1", result.getFirstName());
        assertEquals("ln1", result.getLastName());
    }

    @Test
    void saveContactTest() {
        //given
        ContactDTO contactDTO = ContactDTO.builder()
                .name("1 contact")
                .firstName("1 name")
                .lastName("Doe")
                .address(AddressDTO.builder()
                        .city("Warszawa")
                        .street("Niepodległości 2")
                        .zipCode("20-001")
                        .build())
                .correspondenceAddress(AddressDTO.builder()
                        .city("Warszawa")
                        .street("Niepodległości 2")
                        .zipCode("20-001")
                        .build())
                .email("1email@example.com")
                .phone("555 222 222")
                .dateCreated(LocalDateTime.now())
                .pesel("12345678901")
                .build();
        //when
        service.saveContact(contactDTO);
        //then
        verify(repository, times(1)).save(any());
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
