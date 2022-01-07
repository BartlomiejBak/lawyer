package pl.bartekbak.lawyer.repository;

import pl.bartekbak.lawyer.entity.Lawsuit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LawsuitRepository {

    List<Lawsuit> list();
    Optional<Lawsuit> lawsuitById(UUID id);
    void add(Lawsuit lawsuit);
    void update(Lawsuit lawsuit);
    void deleteById(UUID id);
}
