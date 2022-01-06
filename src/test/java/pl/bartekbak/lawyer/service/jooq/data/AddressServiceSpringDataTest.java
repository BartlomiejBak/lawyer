package pl.bartekbak.lawyer.service.jooq.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.repository.AddressRepository;
import pl.bartekbak.lawyer.service.jooq.AddressServiceJooq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceSpringDataTest {

    @InjectMocks
    AddressServiceJooq service;

    @Mock
    AddressRepository repository;
    
    private final UUID first = UUID.randomUUID();
    private final UUID second = UUID.randomUUID();
    private final UUID third = UUID.randomUUID();

    @Test
    void findAllAddressesTest() {
        // given
        List<Address> list = new ArrayList<>();
        Address addressOne = new Address(first, "street1", "zip1", "city1", "country1");
        Address addressTwo = new Address(second, "street2", "zip2", "city2", "country2");
        Address addressThree = new Address(third, "street3", "zip3", "city3", "country3");

        list.add(addressOne);
        list.add(addressTwo);
        list.add(addressThree);

        when(repository.list()).thenReturn(list);

        // when
        List<AddressDTO> result = service.findAllAddresses();

        // then
        assertEquals(3, result.size());
        verify(repository, times(1)).list();
    }

    @Test
    void findAddressByIdTest() {
        // given
        when(repository.addressById(first))
                .thenReturn(Optional.of(new Address(first, "s1", "z1", "c1", "c1")));

        // when
        AddressDTO result = service.findAddressById(first);

        // then
        assertEquals("s1", result.getStreet());
        assertEquals("z1", result.getZipCode());
    }

    @Test
    void saveAddressTest() {
        // given
        AddressDTO address = new AddressDTO(first, "street1", "zip1", "city1", "country1");

        // when
        service.saveAddress(address);
        service.saveAddress(new AddressDTO());

        // then
        verify(repository, times(2)).add(any());

    }

    @Test
    void deleteAddressByIdTest() {
        // given
        // when
        service.deleteAddressById(first);
        service.deleteAddressById(second);

        // then
        verify(repository, times(1)).deleteById(first);
        verify(repository, times(2)).deleteById(any());
    }
}
