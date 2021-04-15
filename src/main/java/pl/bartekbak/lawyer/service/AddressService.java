package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAllAddresses();

    AddressDTO findAddressById(int id);

    void saveAddress(AddressDTO  address);

    void deleteAddressById(int id);
}
