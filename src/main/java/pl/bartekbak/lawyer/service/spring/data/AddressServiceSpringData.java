package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.AddressRepository;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceSpringData implements AddressService {
    AddressRepository addressRepository;

    @Autowired
    public AddressServiceSpringData(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Address findAddressById(int id) {
        Optional<Address> result = addressRepository.findById(id);
        Address address = null;
        if (result.isPresent()) {
            address = result.get();
        } else {
            throw new RuntimeException("Id not found");
        }
        return address;
    }

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(int id) {
        addressRepository.deleteById(id);
    }
}
