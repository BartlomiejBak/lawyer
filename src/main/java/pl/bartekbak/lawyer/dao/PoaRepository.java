package pl.bartekbak.lawyer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartekbak.lawyer.entity.Poa;

import java.util.List;

public interface PoaRepository extends JpaRepository<Poa, Integer> {

    public List<Poa> findAllByOrderByIdAsc();
}
