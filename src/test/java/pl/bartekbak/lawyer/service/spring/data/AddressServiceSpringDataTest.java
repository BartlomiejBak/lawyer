package pl.bartekbak.lawyer.service.spring.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartekbak.lawyer.dao.AddressRepository;
import pl.bartekbak.lawyer.entity.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceSpringDataTest {

    @InjectMocks
    AddressServiceSpringData service;

    @Mock
    AddressRepository repository;

    @Test
    void findAllAddressesTest() {
        //given
        List<Address> list = new ArrayList<>();
        Address addressOne = new Address(1, "street1", "zip1", "city1", "country1");
        Address addressTwo = new Address(2, "street2", "zip2", "city2", "country2");
        Address addressThree = new Address(3, "street3", "zip3", "city3", "country3");

        list.add(addressOne);
        list.add(addressTwo);
        list.add(addressThree);

        when(repository.findAllByOrderByIdAsc()).thenReturn(list);
        //when
        List<Address> result = service.findAllAddresses();

        //then
        assertEquals(3, result.size());
        verify(repository, times(1)).findAllByOrderByIdAsc();
    }

    @Test
    void findAddressByIdTest() {
        //given
        when(repository.findById(1))
                .thenReturn(Optional.of(new Address(1, "s1", "z1", "c1", "c1")));
        //when
        Address result = service.findAddressById(1);
        //then
        assertEquals("s1", result.getStreet());
        assertEquals("z1", result.getZipCode());
    }

    @Test
    void saveAddressTest() {
        //given
        Address address = new Address(1, "street1", "zip1", "city1", "country1");
        //when
        service.saveAddress(address);
        service.saveAddress(new Address());
        //then
        verify(repository, times(1)).save(address);
        verify(repository, times(2)).save(any());

    }

    @Test
    void deleteAddressByIdTest() {
        //given
        //when
        service.deleteAddressById(1);
        service.deleteAddressById(2);
        //then
        verify(repository, times(1)).deleteById(1);
        verify(repository, times(2)).deleteById(any());
    }
}
