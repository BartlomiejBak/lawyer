package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findAllAddresses();

    public Address findAddressById(int id);

    public void saveAddress(Address address);

    public void deleteAddressById(int id);
}
