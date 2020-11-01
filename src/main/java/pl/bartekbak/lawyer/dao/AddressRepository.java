package pl.bartekbak.lawyer.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bartekbak.lawyer.entity.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    public List<Address> findAllByOrderByIdAsc();
}
