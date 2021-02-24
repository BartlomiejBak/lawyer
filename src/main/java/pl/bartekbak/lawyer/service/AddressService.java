package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAllAddresses();

    Address findAddressById(int id);

    void saveAddress(Address address);

    void deleteAddressById(int id);
}
