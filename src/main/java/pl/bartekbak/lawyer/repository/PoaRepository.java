package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Poa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PoaRepository {

    List<Poa> list();
    Optional<Poa> poaById(UUID id);
    void add(Poa poa);
    void update(Poa poa);
    void deleteById(UUID id);
}
