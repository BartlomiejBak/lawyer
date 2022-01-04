package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {

    List<Address> list();
    Optional<Address> addressById(UUID id);
    void add(Address address);
    void update(Address address);
    void deleteById(UUID id);

}
