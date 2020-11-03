package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    public List<Address> findAllByOrderByIdAsc();
}
