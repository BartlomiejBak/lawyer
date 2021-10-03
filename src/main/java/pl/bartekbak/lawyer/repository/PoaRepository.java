package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Poa;

import java.util.List;
import java.util.Optional;

public interface PoaRepository {

    List<Poa> list();
    Optional<Poa> poaById(int id);
    void add(Poa poa);
    void update(Poa poa);
    void deleteById(int id);
}
