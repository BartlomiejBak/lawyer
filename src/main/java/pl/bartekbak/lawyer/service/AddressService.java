package pl.bartekbak.lawyer.service;

import pl.bartekbak.lawyer.dto.AddressDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    List<AddressDTO> findAllAddresses();

    AddressDTO findAddressById(UUID id);

    void saveAddress(AddressDTO  address);

    void deleteAddressById(UUID id);
}
