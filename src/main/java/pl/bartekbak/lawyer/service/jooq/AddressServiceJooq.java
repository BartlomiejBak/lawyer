package pl.bartekbak.lawyer.service.jooq;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.repository.AddressRepository;
import pl.bartekbak.lawyer.service.AddressService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceJooq implements AddressService {

    AddressRepository addressRepository;

    public AddressServiceJooq(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> findAllAddresses() {
        return addressRepository.list()
                .stream()
                .map(Address::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findAddressById(UUID id) {
        Optional<Address> result = addressRepository.addressById(id);
        AddressDTO address;
        address = result.map(Address::toDto).orElse(null);
        return address;
    }

    @Override
    public void saveAddress(AddressDTO address) {
        addressRepository.add(Address.fromDto(address));
    }

    @Override
    public void deleteAddressById(UUID id) {
        addressRepository.deleteById(id);
    }
}
