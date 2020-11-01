package pl.bartekbak.lawyer.service.spring.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartekbak.lawyer.dao.AddressRepository;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.service.AddressService;

import java.util.List;

@Service
public class AddressServiceSpringData implements AddressService {
    AddressRepository addressRepository;

    @Autowired
    public AddressServiceSpringData(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAllAddresses() {
        return null;
    }
}
