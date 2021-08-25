package pl.bartekbak.lawyer.repository.jooq;

import org.springframework.stereotype.Repository;
import pl.bartekbak.lawyer.entity.Address;
import pl.bartekbak.lawyer.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public List<Address> list() {
        return List.of();
    }

    @Override
    public Optional<Address> addressById(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Address address) {

    }

    @Override
    public void deleteById(int id) {

    }
}
