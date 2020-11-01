package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.entity.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findAllAddresses();
}
