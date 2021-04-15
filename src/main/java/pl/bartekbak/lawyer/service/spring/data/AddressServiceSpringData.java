package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.AddressRepository;
import pl.bartekbak.lawyer.dto.AddressDTO;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceSpringData implements AddressService {

    AddressRepository addressRepository;

    public AddressServiceSpringData(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> findAllAddresses() {
        return addressRepository.findAllByOrderByAddressIdAsc()
                .stream()
                .map(Address::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findAddressById(int id) {
        Optional<Address> result = addressRepository.findById(id);
        AddressDTO address;
        if (result.isPresent()) {
            address = result.get().toDto();
        } else {
            throw new RuntimeException("Id not found");
        }
        return address;
    }

    @Override
    public void saveAddress(AddressDTO address) {
        addressRepository.save(Address.fromDto(address));
    }

    @Override
    public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
    }
}
